package com.ake.system.service.impl;

import com.ake.common.datascope.annotation.DataScope;
import com.ake.common.security.utils.SecurityUtils;
import com.ake.nbems.common.core.domain.R;
import com.ake.nbems.common.core.exception.CustomException;
import com.ake.nbems.common.core.utils.StringUtils;
import com.ake.nbems.common.core.web.domain.AjaxResult;
import com.ake.system.api.domain.SysRole;
import com.ake.system.api.domain.SysUser;
import com.ake.system.api.model.UserInfo;
import com.ake.system.domain.SysUserPost;
import com.ake.system.domain.SysUserRole;
import com.ake.system.domain.query.SysUserQuery;
import com.ake.system.mapper.*;
import com.ake.system.service.ISysPermissionService;
import com.ake.system.service.ISysUserPostService;
import com.ake.system.service.ISysUserRoleService;
import com.ake.system.service.ISysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Description
 * @Author yezt
 * @Date 2021-12-13 15:41:46
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private ISysUserRoleService sysUserRoleService;
    @Autowired
    private SysPostMapper sysPostMapper;
    @Autowired
    private ISysUserPostService sysUserPostService;
    @Autowired
    private ISysPermissionService sysPermissionService;

    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<SysUser> selectUserList(SysUserQuery query) {
        if (query.getId() == null) {
            query.setId(SecurityUtils.getLoginUser().getUserId());
        }
        List<SysUser> userList = sysUserMapper.selectUserList(query);
        for (SysUser sysUser : userList) {
            List<SysRole> sysRoles = sysRoleMapper.selectRolePermissionByUserId(sysUser.getId());
            sysUser.setRoles(sysRoles);
        }
        return userList;
    }

    @Override
    public R<UserInfo> selectUserByUserName(String username) {
        SysUser sysUser = sysUserMapper.selectUserByUserName(username);
        if (StringUtils.isNull(sysUser)) {
            return R.fail("????????????????????????");
        }
        // ????????????
        Set<String> roles = sysPermissionService.getRolePermission(sysUser.getId());
        // ????????????
        Set<String> permissions = sysPermissionService.getMenuPermission(sysUser.getId());

        UserInfo sysUserVo = new UserInfo();
        sysUserVo.setSysUser(sysUser);
        sysUserVo.setRoles(roles);
        sysUserVo.setPermissions(permissions);
        return R.ok(sysUserVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertUser(SysUser sysUser) {
        // ??????????????????
        int rows = sysUserMapper.insert(sysUser);
        // ????????????????????????
        insertUserPost(sysUser);
        // ???????????????????????????
        insertUserRole(sysUser);
        return rows;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUser(SysUser sysUser) {
        Long userId = sysUser.getId();
        // ???????????????????????????
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("userId", userId);
        sysUserRoleService.remove(wrapper);
        // ???????????????????????????
        insertUserRole(sysUser);
        // ???????????????????????????
        insertUserPost(sysUser);
        return sysUserMapper.updateById(sysUser);
    }

    @Override
    public AjaxResult deleteUserByIds(Long[] ids) {
        List<Long> list = new ArrayList<>();
        for (Long id : ids) {
            if(SecurityUtils.getLoginUser().getUserId().equals(id)){
                return AjaxResult.error("????????????????????????");
            }
            SysUser sysUser = new SysUser();
            sysUser.setId(id);
            checkUserAllowed(sysUser);
            list.add(id);
        }
        boolean flag = super.removeByIds(list);
        return flag ? AjaxResult.success("????????????") : AjaxResult.error("????????????");
    }

    public void insertUserPost(SysUser sysUser) {
        Long[] posts = sysUser.getPostIds();
        if (StringUtils.isNotNull(posts)) {
            // ???????????????????????????
            List<SysUserPost> list = new ArrayList<SysUserPost>();
            for (Long postId : posts) {
                SysUserPost up = new SysUserPost();
                up.setUserId(sysUser.getId());
                up.setPostId(postId);
                list.add(up);
            }
            if (list.size() > 0) {
                sysUserPostService.saveBatch(list);
            }
        }
    }

    public void insertUserRole(SysUser user) {
        Long[] roles = user.getRoleIds();
        if (StringUtils.isNotNull(roles)) {
            // ???????????????????????????
            List<SysUserRole> list = new ArrayList<SysUserRole>();
            for (Long roleId : roles) {
                SysUserRole ur = new SysUserRole();
                ur.setUserId(user.getId());
                ur.setRoleId(roleId);
                list.add(ur);
            }
            if (list.size() > 0) {
                sysUserRoleService.saveBatch(list);
            }
        }
    }

    /**
     * ??????????????????????????????
     *
     * @param user ????????????
     */
    public void checkUserAllowed(SysUser user) {
        if (StringUtils.isNotNull(user.getId()) && user.isAdmin()) {
            throw new CustomException("????????????????????????????????????");
        }
    }
}
