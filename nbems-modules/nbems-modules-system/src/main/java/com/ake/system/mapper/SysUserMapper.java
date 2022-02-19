package com.ake.system.mapper;

import com.ake.system.api.domain.SysUser;
import com.ake.system.domain.query.SysUserQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description
 * @Author yezt
 * @Date 2021-12-13 15:41:46
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
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
    SysUser selectUserByUserName(String username);
}
