package com.ake.system.service.impl;

import com.ake.system.api.domain.SysUser;
import com.ake.system.service.ISysMenuService;
import com.ake.system.service.ISysPermissionService;
import com.ake.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yezt
 * @description
 * @date 2021/12/15 11:45
 */
@Service
public class SysPermissionServiceImpl implements ISysPermissionService {
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ISysMenuService sysMenuService;

    @Override
    public Set<String> getRolePermission(Long userId) {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (SysUser.isAdmin(userId)) {
            roles.add("admin");
        } else {
            roles.addAll(sysRoleService.selectRolePermissionByUserId(userId));
        }
        return roles;
    }

    @Override
    public Set<String> getMenuPermission(Long userId) {
        Set<String> perms = new HashSet<String>();
        // 管理员拥有所有权限
        if (SysUser.isAdmin(userId)) {
            perms.add("*:*:*");
        } else {
            perms.addAll(sysMenuService.selectMenuPermsByUserId(userId));
        }
        return perms;
    }
}
