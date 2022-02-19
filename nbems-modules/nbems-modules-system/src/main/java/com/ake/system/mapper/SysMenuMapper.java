package com.ake.system.mapper;

import com.ake.system.domain.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description
 * @Author yezt
 * @Date 2021-12-15 14:27:20
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 根据用户ID查询权限
     * @param userId 用户ID
     * @throws
     * @return java.util.List<java.lang.String>
     */
    List<String> selectMenuPermsByUserId(Long userId);
}
