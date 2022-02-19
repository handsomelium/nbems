package com.ake.nbems.auth.config;

import com.ake.nbems.common.core.utils.ServletUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

/**
 * @author yezt
 * @description
 * @date 2021/12/15 17:28
 */
@Component
public class LoginFeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Proxy-Client-IP", ServletUtils.getRequest().getHeader("Proxy-Client-IP"));
        requestTemplate.header("X-Forwarded-For", ServletUtils.getRequest().getHeader("X-Forwarded-For"));
        requestTemplate.header("WL-Proxy-Client-IP", ServletUtils.getRequest().getHeader("WL-Proxy-Client-IP"));
        requestTemplate.header("X-Real-IP", ServletUtils.getRequest().getHeader("X-Real-IP"));
    }
}
