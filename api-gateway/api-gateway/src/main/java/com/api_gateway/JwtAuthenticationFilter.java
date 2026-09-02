package com.api_gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    public static final String AUTH_USER_HEADER = "X-Auth-User";

    private static final Logger logger =
            LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 10;
    }

    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange,
            GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();

        String path = request.getPath().value();
        HttpMethod method = request.getMethod();

        ServerHttpRequest.Builder mutatedRequest =
                request.mutate()
                        .headers(headers ->
                                headers.remove(AUTH_USER_HEADER));

        if (isPublic(method, path)) {
            exchange = exchange.mutate()
                    .request(mutatedRequest.build())
                    .build();

            return chain.filter(exchange);
        }

        String authHeader =
                request.getHeaders().getFirst("Authorization");

        if (authHeader == null ||
                !authHeader.startsWith("Bearer ")) {

            return unauthorized(exchange, "Missing bearer token");
        }

        String token = authHeader.substring(7);

        String username;

        try {
            username = jwtService.extractUsername(token);
        } catch (Exception e) {
            return unauthorized(exchange, "Invalid or expired token");
        }

        if (username == null || username.isBlank()) {
            return unauthorized(exchange, "Token has no subject");
        }

        ServerHttpRequest authedRequest =
                mutatedRequest
                        .header(AUTH_USER_HEADER, username)
                        .build();

        exchange = exchange.mutate()
                .request(authedRequest)
                .build();

        return chain.filter(exchange);
    }

    private boolean isPublic(
            HttpMethod method,
            String path) {

        if (!HttpMethod.POST.equals(method)) {
            return false;
        }

        return pathMatcher.match("/login", path)
                || pathMatcher.match("/login/**", path)
                || pathMatcher.match("/users", path);
    }

    private Mono<Void> unauthorized(
            ServerWebExchange exchange,
            String reason) {

        logger.warn(
                "Rejecting request to {} -> {}",
                exchange.getRequest().getPath(),
                reason);

        exchange.getResponse()
                .setStatusCode(HttpStatus.UNAUTHORIZED);

        return exchange.getResponse().setComplete();
    }
}