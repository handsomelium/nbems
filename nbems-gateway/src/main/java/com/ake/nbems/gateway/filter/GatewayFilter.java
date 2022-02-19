package com.ake.nbems.gateway.filter;

import com.ake.nbems.common.core.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class GatewayFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //  nginx 会从请求头中设置 客户端的真实ip放入网关
        String sourceIp = exchange.getRequest().getHeaders().getFirst("X-Real-IP");
//        if (StringUtils.isEmpty(sourceIp)) {
//            ServerHttpResponse response = exchange.getResponse();
//            response.setStatusCode(HttpStatus.BAD_REQUEST);
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("code", "500");
//            jsonObject.put("msg", "sourceIp is null");
//            DataBuffer buffer = response.bufferFactory().wrap(jsonObject.toJSONString().getBytes());
//            return response.writeWith(Mono.just(buffer));
//        }
        // 使用网关过滤
        return chain.filter(exchange);
    }
 
}
