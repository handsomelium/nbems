package com.ake.nbems.gateway.filter;

import com.ake.common.redis.service.RedisService;
import com.ake.nbems.common.core.constant.Constants;
import com.ake.nbems.common.core.utils.StringUtils;
import com.ake.nbems.common.core.web.domain.AjaxResult;
import com.ake.nbems.gateway.service.ValidateCodeService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author yezt
 * @description swagger过滤器
 * @date 2021/12/16 16:22
 */
@Component
public class ValidateCodeFilter extends AbstractGatewayFilterFactory<Object> {
    private final static String AUTH_URL = "/oauth/token";

    @Autowired
    private ValidateCodeService validateCodeService;

    private static final String BASIC_ = "Basic ";

    private static final String CODE = "code";

    private static final String UUID = "uuid";
    
    private static final String GRANT_TYPE = "grant_type";
    
    private static final String REFRESH_TOKEN = "refresh_token";
    
    private static final String CLIENT_CREDENTIALS = "client_credentials";
    
    private static final String AUTHORIZATION_CODE = "authorization_code";

    @Autowired
    private RedisService redis;

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // 非登录请求，不处理
            if (!StringUtils.containsIgnoreCase(request.getURI().getPath(), AUTH_URL)) {
                return chain.filter(exchange);
            }
            
            // 刷新token请求，不处理
            String grantType = request.getQueryParams().getFirst(GRANT_TYPE);
            if (StringUtils.containsIgnoreCase(request.getURI().getPath(), AUTH_URL) && StringUtils.containsIgnoreCase(grantType, REFRESH_TOKEN)) {
               return chain.filter(exchange);
            }
            
            // 客户端模式，不处理
            if (StringUtils.containsIgnoreCase(request.getURI().getPath(), AUTH_URL) && StringUtils.containsIgnoreCase(grantType, CLIENT_CREDENTIALS)) {
                return chain.filter(exchange);
            }
            
            // 授权码模式，不处理
            if (StringUtils.containsIgnoreCase(request.getURI().getPath(), AUTH_URL) && StringUtils.containsIgnoreCase(grantType, AUTHORIZATION_CODE)) {
                return chain.filter(exchange);
            }
            // 消息头存在内容，且不存在验证码参数，不处理
            String header = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (StringUtils.isNotEmpty(header) && StringUtils.startsWith(header, BASIC_)
                    && !request.getQueryParams().containsKey(CODE) && !request.getQueryParams().containsKey(UUID)) {
                return chain.filter(exchange);
            }
            try {
            	String username = request.getQueryParams().getFirst("username");
            	Object retry = redis.getCacheObject(Constants.GATEWAY_CODETRY_KEY+username);
            	//非云平台登录不用验证码
            	if(username.startsWith("csduser-")) {
            		//客服系统
            		return chain.filter(exchange);
            	}
            	
            	//前三次无需判断验证码
            	if(retry==null) {
            		return chain.filter(exchange);
            	} else if ((int)retry < 3){
            		return chain.filter(exchange);
            	} else {
            		validateCodeService.checkCapcha(request.getQueryParams().getFirst(CODE),
                            request.getQueryParams().getFirst(UUID));
            	}
            } catch (Exception e) {
                ServerHttpResponse response = exchange.getResponse();
                response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
                return exchange.getResponse().writeWith(
                        Mono.just(response.bufferFactory().wrap(JSON.toJSONBytes(AjaxResult.error(e.getMessage())))));
            }
            return chain.filter(exchange);
        };
    }
}
