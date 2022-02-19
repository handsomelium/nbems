package com.ake.common.security.feign;

import feign.RequestInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import java.util.Arrays;

/**
 * Feign配置注册
 *
 * @author guojm
 **/
@Configuration
public class OAuth2FeignConfig
{
	@Bean
    @ConfigurationProperties(prefix = "security.oauth2.client")
    public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
        return new ClientCredentialsResourceDetails();
    }

    @Bean
    public RequestInterceptor requestInterceptor()
    {
        return new OAuth2FeignRequestInterceptor();
    }

    @Bean(name = "NbemsOAuthRestTemplate")
    @LoadBalanced
    public OAuth2RestOperations restTemplate(SpringClientFactory clientFactory, ClientCredentialsResourceDetails oauth2ClientCredentialsResourceDetails) {
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(oauth2ClientCredentialsResourceDetails);
        // customizer.customize(restTemplate);

        RibbonLoadBalancerClient ribbonLoadBalancerClient = new RibbonLoadBalancerClient(clientFactory);
        LoadBalancerInterceptor loadBalancerInterceptor = new LoadBalancerInterceptor(ribbonLoadBalancerClient);
        ClientCredentialsAccessTokenProvider accessTokenProvider = new ClientCredentialsAccessTokenProvider();
        accessTokenProvider.setInterceptors(Arrays.asList(loadBalancerInterceptor));
        restTemplate.setAccessTokenProvider(accessTokenProvider);
        return restTemplate;
    }

}
