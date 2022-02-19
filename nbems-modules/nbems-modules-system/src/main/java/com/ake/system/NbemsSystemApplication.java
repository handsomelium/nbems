package com.ake.system;

import com.ake.common.security.annotation.EnableCustomConfig;
import com.ake.common.security.annotation.EnableNbemsFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yzt
 * @description
 * @date 2021/12/8 11:51
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCustomConfig
@EnableNbemsFeignClients
public class NbemsSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(NbemsSystemApplication.class);
    }
}
