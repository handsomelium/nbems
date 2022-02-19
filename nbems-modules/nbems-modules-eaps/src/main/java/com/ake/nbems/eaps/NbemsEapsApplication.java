package com.ake.nbems.eaps;

import com.ake.common.security.annotation.EnableNbemsFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
@EnableDiscoveryClient
// @EnableCustomConfig
@EnableNbemsFeignClients
public class NbemsEapsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NbemsEapsApplication.class, args);
	}

}
