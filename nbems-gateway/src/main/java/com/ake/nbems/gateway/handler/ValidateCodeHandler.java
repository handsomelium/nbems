package com.ake.nbems.gateway.handler;

import com.ake.common.redis.service.RedisService;
import com.ake.nbems.common.core.constant.Constants;
import com.ake.nbems.common.core.exception.CaptchaException;
import com.ake.nbems.common.core.utils.StringUtils;
import com.ake.nbems.common.core.web.domain.AjaxResult;
import com.ake.nbems.gateway.service.ValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 * @author yezt
 * @description swagger处理器
 * @date 2021/12/16 16:22
 */
@Component
public class ValidateCodeHandler implements HandlerFunction<ServerResponse> {
    @Autowired
    private ValidateCodeService validateCodeService;
    
    @Autowired
    private RedisService redis;

    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
        AjaxResult ajax;
        try {
        	int retry = checkUsername(serverRequest);
        	if(retry<3) {
        		ajax = AjaxResult.success("免验证码");
        	} else {
        		ajax = validateCodeService.createCapcha();
        	}
        	ajax.put("retry", retry);
        } catch (CaptchaException | IOException e) {
            return Mono.error(e);
        }
        return ServerResponse.status(HttpStatus.OK).body(BodyInserters.fromValue(ajax));
    }
    
    private Integer checkUsername(ServerRequest serverRequest) {
    	String username = serverRequest.queryParams().getFirst("username");
    	if(!StringUtils.isEmpty(username)) {
    		Object retry = redis.getCacheObject(Constants.GATEWAY_CODETRY_KEY+username);
        	if(retry==null) {
        		return 0;
        	} else {
        		return (int)retry;
        	}
    	} else {
    		return 0;
    	}
    }
}