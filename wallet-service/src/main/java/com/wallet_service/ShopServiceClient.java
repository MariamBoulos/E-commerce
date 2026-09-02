package com.wallet_service;

import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Service
public class ShopServiceClient {

    private final ShopProxy shopProxy;
    private final CircuitBreakerFactory<?, ?> circuitBreakerFactory;

    public ShopServiceClient(
            ShopProxy shopProxy,
            CircuitBreakerFactory<?, ?> circuitBreakerFactory) {

        this.shopProxy = shopProxy;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    public CartInfo createCart(Integer userId,String username) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("shop-service");
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        UserRequest request = new UserRequest();
        request.setUserId(userId);

        return circuitBreaker.run(
                () -> RequestContextPropagation.withContext(
                        requestAttributes,
                        () -> shopProxy.createCart(username,request)),
                throwable -> {
                    throwable.printStackTrace();
                    return null;
                }
        );
    }

    public void deleteCartByUserId(Integer userId) {

        CircuitBreaker circuitBreaker =
                circuitBreakerFactory.create("shop-service");

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        UserRequest request = new UserRequest();
        request.setUserId(userId);

        circuitBreaker.run(
                () -> RequestContextPropagation.withContext(requestAttributes, () -> {
                    shopProxy.deleteCartByUserId(request);
                    return null;
                }),
                throwable -> {
                    throwable.printStackTrace();
                    return null;
                }
        );
    }
}