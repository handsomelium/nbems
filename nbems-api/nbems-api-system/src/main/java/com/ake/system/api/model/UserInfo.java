package com.ake.system.api.model;

import com.ake.system.api.domain.SysUser;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author yzt
 * @description 用户信息
 * @date 2021/12/13 15:45
 */
@Data
public class UserInfo implements Serializable {
    /**
     * 用户基本信息
     */
    private SysUser sysUser;

    /**
     * 权限标识集合
     */
    private Set<String> permissions;

    /**
     * 角色集合
     */
    private Set<String> roles;
}
