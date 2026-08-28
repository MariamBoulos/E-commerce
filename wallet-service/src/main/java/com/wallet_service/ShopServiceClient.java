package com.wallet_service;

import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

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

    public CartInfo createCart(Integer userId) {

        CircuitBreaker circuitBreaker =
                circuitBreakerFactory.create("shop-service");

        return circuitBreaker.run(
                () -> shopProxy.createCart(userId),
                throwable -> {
                    System.out.println("Shop service is unavailable.");
                    return null;
                }
        );
    }

    public void deleteCartByUserId(Integer userId) {

        CircuitBreaker circuitBreaker =
                circuitBreakerFactory.create("shop-service");

        circuitBreaker.run(
                () -> {
                    shopProxy.deleteCartByUserId(userId);
                    return null;
                },
                throwable -> {
                    System.out.println("Shop service is unavailable.");
                    return null;
                }
        );
    }
}