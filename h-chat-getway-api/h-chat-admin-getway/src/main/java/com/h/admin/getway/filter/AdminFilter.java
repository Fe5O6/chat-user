package com.h.admin.getway.filter;


import com.h.admin.getway.util.AppJwtUtil;
import com.h.model.common.constant.AdminConstant;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 用户登录拦截器
 */
@Component
@Slf4j
public class AdminFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取request和response对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //判断是否是登录请求
        if (request.getURI().getPath().contains(AdminConstant.LOGIN)) {
            //放行
            return chain.filter(exchange);
        }

        //判断token是否存在
        String token = request.getHeaders().getFirst(AdminConstant.TOKEN);
        if (StringUtils.isBlank(token)) {
            //返回自定义状态码401
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        try {
            //判断key是否有效，解析token
            Claims claimsBody = AppJwtUtil.getClaimsBody(token);

            //判断token是否过期
            int status = AppJwtUtil.verifyToken(claimsBody);

            if (AdminConstant.NO_STATUS_1 == status || AdminConstant.NO_STATUS_2 == status) {
                //返回自定义状态码401
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }

            //获取用户id
            String userId = claimsBody.get(AdminConstant.ID).toString();

            //设置请求头
            ServerHttpRequest serverHttpRequest = request.mutate()
                    .headers(httpHeaders -> httpHeaders.add(AdminConstant.HEADER_NAME, userId))
                    .build();

            //重置request请求
            exchange.mutate().request(serverHttpRequest).build();
        } catch (Exception e) {
            log.error("admin微服务gataWay异常，异常原因:", e);
            //返回自定义状态码401
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        return chain.filter(exchange);
    }

    /**
     * 过滤器优先级  值越小，优先级越高
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
