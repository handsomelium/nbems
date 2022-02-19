package com.ake.system.mapper;

import com.ake.system.domain.User;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yezt
 * @description
 * @date 2021/12/23 15:32
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
