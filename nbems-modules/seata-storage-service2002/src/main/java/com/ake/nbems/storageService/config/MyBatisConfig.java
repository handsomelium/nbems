package com.ake.nbems.storageService.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lyrstart
 * @create 2021-12-17 13:48
 */

@Configuration
@MapperScan({"com.ake.nbems.storageService.dao"})
public class MyBatisConfig {
}
