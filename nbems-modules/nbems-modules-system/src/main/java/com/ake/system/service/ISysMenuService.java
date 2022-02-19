package com.ake.system.service;

import com.ake.system.domain.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * @Description
 * @Author yezt
 * @Date 2021-12-15 14:27:20
 */
public interface ISysMenuService extends IService<SysMenu> {
    /**
     *
     * @param userId 
     * @throws           
     * @return java.util.Set<java.lang.String>
     */
    Set<String> selectMenuPermsByUserId(Long userId);
}
