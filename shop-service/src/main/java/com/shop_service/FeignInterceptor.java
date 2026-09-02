package com.shop_service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;

@Configuration
public class FeignInterceptor {

    @Bean
    public RequestInterceptor requestInterceptor() {

        return template -> {

            ServletRequestAttributes attributes =
                    (ServletRequestAttributes)
                            RequestContextHolder.getRequestAttributes();

            if (attributes == null) {
                return;
            }

            String username =
                    attributes.getRequest()
                            .getHeader("X-Auth-User");

            if (username != null && !username.isBlank()) {
                template.header("X-Auth-User", username);
            }
        };
    }
}