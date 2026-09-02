package com.wallet_service;

import java.util.function.Supplier;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class RequestContextPropagation {

    public static <T> T withContext(RequestAttributes attributes, Supplier<T> action) {

        if (attributes != null) {
            RequestContextHolder.setRequestAttributes(attributes);
        }
        try {
            return action.get();
        } finally {
            RequestContextHolder.resetRequestAttributes();
        }
    }
}