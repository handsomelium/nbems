package com.ake.system.service;

import com.ake.system.api.domain.SysRole;
import com.ake.system.domain.query.SysRoleQuery;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * @Description
 * @Author yezt
 * @Date 2021-12-14 09:01:27
 */
public interface ISysRoleService extends IService<SysRole> {
    /**
     * 根据条件分页查询角色列表
     * @param query
     * @throws
     * @return java.util.List<com.ake.system.api.domain.SysRole>
     */
    List<SysRole> selectRoleList(SysRoleQuery query);
    /**
     * 根据用户ID查询角色
     * @param userId 用户ID
     * @throws           
     * @return java.util.Set<java.lang.String> 权限列表
     */
    Set<String> selectRolePermissionByUserId(Long userId);
}
