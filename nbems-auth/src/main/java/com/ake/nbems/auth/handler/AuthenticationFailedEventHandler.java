package com.ake.nbems.auth.handler;

import com.ake.common.redis.service.RedisService;
import com.ake.nbems.common.core.constant.Constants;
import com.ake.nbems.common.core.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author yezt
 * @description 认证失败处理器
 * @date 2021/12/16 8:57
 */
@Component
public class AuthenticationFailedEventHandler implements ApplicationListener<AbstractAuthenticationEvent> {
    @Autowired
    private RedisService redisService;

    @Override
    public void onApplicationEvent(AbstractAuthenticationEvent abstractAuthenticationEvent) {
        String username = ServletUtils.getParameter("username");
        Object retry = redisService.getCacheObject(Constants.GATEWAY_CODETRY_KEY+username);
        int codetry = 0;
        //前三次无需判断验证码
        if(retry==null) {
            codetry = 1;
        } else {
            codetry = (int)retry + 1;
        }
        redisService.setCacheObject(Constants.GATEWAY_CODETRY_KEY+username, codetry, Constants.CODETRY_EXPIRATION, TimeUnit.MINUTES);
    }
}
