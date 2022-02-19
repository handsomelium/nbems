package com.ake.system.mapper;

import com.ake.system.api.domain.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description
 * @Author yezt
 * @Date 2021-12-14 09:01:27
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 根据用户ID查询角色
     * @param userId 用户id
     * @throws
     * @return java.util.List<com.ake.system.api.domain.SysRole>
     */
    List<SysRole> selectRolePermissionByUserId(Long userId);
}
