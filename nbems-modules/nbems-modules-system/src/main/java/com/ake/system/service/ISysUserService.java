package com.ake.system.service;

import com.ake.nbems.common.core.domain.R;
import com.ake.nbems.common.core.web.domain.AjaxResult;
import com.ake.system.api.domain.SysUser;
import com.ake.system.api.model.UserInfo;
import com.ake.system.domain.query.SysUserQuery;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author yzt
 * @description
 * @date 2021/12/13 17:16
 */
public interface ISysUserService extends IService<SysUser> {
    /**
     * 查询用户信息
     * @param query
     * @throws
     * @return java.util.List<com.ake.system.api.domain.SysUser>
     */
    List<SysUser> selectUserList(SysUserQuery query);
    /**
     * 根据用户名查询用户信息
     * @param username
     * @throws
     * @return com.ake.system.api.domain.SysUser
     */
    R<UserInfo> selectUserByUserName(String username);
    /**
     * 新增用户
     * @param sysUser
     * @throws
     * @return int
     */
    int insertUser(SysUser sysUser);
    /**
     * 修改用户
     * @param sysUser
     * @throws
     * @return int
     */
    int updateUser(SysUser sysUser);
    /**
     * 根据id删除用户
     * @param ids 
     * @throws           
     * @return boolean
     */
    AjaxResult deleteUserByIds(Long[] ids);
}
