package com.ake.system.controller;

import com.ake.common.security.utils.SecurityUtils;
import com.ake.nbems.common.core.domain.R;
import com.ake.nbems.common.core.utils.StringUtils;
import com.ake.nbems.common.core.web.controller.BaseController;
import com.ake.nbems.common.core.web.domain.AjaxResult;
import com.ake.nbems.common.core.web.page.TableDataInfo;
import com.ake.system.api.domain.SysUser;
import com.ake.system.api.model.UserInfo;
import com.ake.system.domain.query.SysUserQuery;
import com.ake.system.service.ISysPermissionService;
import com.ake.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @author yezt
 * @description
 * @date 2021/12/13 15:07
 */
@Api(tags = {"用户管理"})
@RestController
@RequestMapping("/user")
public class SysUserController extends BaseController {
    @Autowired
    private ISysUserService sysUserService;

    @ApiOperation("查询用户列表")
    @GetMapping("/list")
    public TableDataInfo<SysUser> findUser(SysUserQuery query) {
        startPage();
        List<SysUser> userList = sysUserService.selectUserList(query);
        return getDataTable(userList);
    }

    @ApiOperation("获取当前用户信息")
    @GetMapping("/info/{username}")
    public R<UserInfo> info(@PathVariable("username") String username) {
        return sysUserService.selectUserByUserName(username);
    }

    @ApiOperation("添加用户")
    @PostMapping
    public AjaxResult addUser(@Validated SysUser sysUser) {
        sysUser.setPassword(SecurityUtils.encryptPassword(sysUser.getPassword()));
        return toAjax(sysUserService.insertUser(sysUser));
    }

    @ApiOperation("修改用户信息（所有用户基础信息）")
    @PutMapping
    public AjaxResult edit(@Validated SysUser sysUser) {
        return toAjax(sysUserService.updateUser(sysUser));
    }

    @ApiOperation("编辑用户信息（个人基础信息）")
    @PutMapping("/editUser")
    public AjaxResult editUser(@Validated SysUser sysUser) {
        boolean flag = sysUserService.updateById(sysUser);
        return flag ? AjaxResult.success("更新成功") : AjaxResult.error("更新失败");
    }

    @GetMapping("/all")
    @PreAuthorize("@ss.hasPermi('system:user:list')")
    public R<?> all() {
        List<SysUser> list = sysUserService.list();
        return R.ok(list);
    }

    @ApiOperation("删除用户")
    @DeleteMapping
    public AjaxResult remove(Long[] ids) {
        return sysUserService.deleteUserByIds(ids);
    }

}
