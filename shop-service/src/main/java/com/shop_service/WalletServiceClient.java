package com.shop_service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceClient {

    private final WalletProxy walletProxy;
    private final CircuitBreakerFactory<?, ?> circuitBreakerFactory;

    public WalletServiceClient(
            WalletProxy walletProxy,
            CircuitBreakerFactory<?, ?> circuitBreakerFactory) {

        this.walletProxy = walletProxy;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    public Optional<WalletInfo> getWallet(Integer userId) {

        CircuitBreaker circuitBreaker =
                circuitBreakerFactory.create("wallet-service");

        return circuitBreaker.run(
                () -> walletProxy.getWallet(userId),
                throwable -> {
                    System.out.println("Wallet service is unavailable.");
                    return Optional.empty();
                }
        );
    }

    public void deposit(BigDecimal amount, Integer userId) {

        CircuitBreaker circuitBreaker =
                circuitBreakerFactory.create("wallet-service");

        circuitBreaker.run(
                () -> {
                    walletProxy.deposit(amount, userId);
                    return null;
                },
                throwable -> {
                    System.out.println("Wallet service is unavailable.");
                    return null;
                }
        );
    }

    public void withdrawal(BigDecimal amount, Integer userId) {

        CircuitBreaker circuitBreaker =
                circuitBreakerFactory.create("wallet-service");

        circuitBreaker.run(
                () -> {
                    walletProxy.withdrawal(amount, userId);
                    return null;
                },
                throwable -> {
                    System.out.println("Wallet service is unavailable.");
                    return null;
                }
        );
    }

    public List<TransactionInfo> history(Integer userId) {

        CircuitBreaker circuitBreaker =
                circuitBreakerFactory.create("wallet-service");

        return circuitBreaker.run(
                () -> walletProxy.history(userId),
                throwable -> {
                    System.out.println("Wallet service is unavailable.");
                    return List.of();
                }
        );
    }
}