package com.ake.system.service.impl;

import com.ake.system.domain.User;
import com.ake.system.mapper.UserMapper;
import com.ake.system.service.UserService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author yezt
 * @description
 * @date 2021/12/23 15:42
 */
@Service
@DS("clickhouse")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
