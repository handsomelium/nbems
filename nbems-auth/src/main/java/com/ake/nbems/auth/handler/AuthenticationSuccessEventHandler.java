package com.ake.nbems.auth.handler;

import com.ake.common.redis.service.RedisService;
import com.ake.common.security.domain.LoginUser;
import com.ake.nbems.common.core.constant.Constants;
import com.ake.nbems.common.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @author yezt
 * @description 认证成功处理器
 * @date 2021/12/16 9:06
 */
@Component
public class AuthenticationSuccessEventHandler implements ApplicationListener<AuthenticationSuccessEvent> {
    @Autowired
    private RedisService redisService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent authenticationSuccessEvent) {
        Authentication authentication = (Authentication) authenticationSuccessEvent.getSource();
        if (StringUtils.isNotEmpty(authentication.getAuthorities())
                && authentication.getPrincipal() instanceof LoginUser) {
            LoginUser user = (LoginUser) authentication.getPrincipal();

            String username = user.getUsername();

            if(!user.getIsWx()) {
                // 记录用户登录日志
//                remoteLogService.saveLogininfor(username, Constants.LOGIN_SUCCESS, "登录成功");

                //清理登陆失败次数记录
//                String ip = IpUtils.getIpAddr(request);
                redisService.deleteObject(Constants.GATEWAY_CODETRY_KEY+username);
            }

        }
    }
}
