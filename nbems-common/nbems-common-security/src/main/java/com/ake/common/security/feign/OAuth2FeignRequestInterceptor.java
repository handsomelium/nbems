package com.ake.common.security.feign;

import com.ake.nbems.common.core.constant.SecurityConstants;
import com.ake.nbems.common.core.utils.ServletUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 * feign 请求拦截器
 *
 * @author guojm
 */
@Component
public class OAuth2FeignRequestInterceptor implements RequestInterceptor
{
	@Autowired
	@LoadBalanced
	private OAuth2RestOperations tokenTemplate;

	//private static final Logger log = LoggerFactory.getLogger(OAuth2FeignRequestInterceptor.class);

    @Override
    public void apply(RequestTemplate requestTemplate)
    {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
    	if (authentication != null && authentication.getDetails() instanceof OAuth2AuthenticationDetails)
        {
            OAuth2AuthenticationDetails dateils = (OAuth2AuthenticationDetails) authentication.getDetails();
            requestTemplate.header(HttpHeaders.AUTHORIZATION,
                    String.format("%s %s", SecurityConstants.BEARER_TOKEN_TYPE, dateils.getTokenValue()));
            requestTemplate.header("Proxy-Client-IP", ServletUtils.getRequest().getHeader("Proxy-Client-IP"));
            requestTemplate.header("X-Forwarded-For", ServletUtils.getRequest().getHeader("X-Forwarded-For"));
            requestTemplate.header("WL-Proxy-Client-IP", ServletUtils.getRequest().getHeader("WL-Proxy-Client-IP"));
            requestTemplate.header("X-Real-IP", ServletUtils.getRequest().getHeader("X-Real-IP"));
        } else {
        	OAuth2AccessToken token = tokenTemplate.getAccessToken();
        	requestTemplate.header(HttpHeaders.AUTHORIZATION,
                    String.format("%s %s", SecurityConstants.BEARER_TOKEN_TYPE, token.getValue()));
        }

    }

}