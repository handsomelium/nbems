package com.ake.nbems.auth;

import com.ake.common.security.annotation.EnableNbemsFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableNbemsFeignClients
public class NbemsAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(NbemsAuthApplication.class, args);
    }

}
