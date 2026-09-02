package com.shop_service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

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

    public Optional<WalletInfo> getWallet(Integer walletId) {
    	 WalletRequest request = new WalletRequest();
         request.setWalletId(walletId);
    	 return walletProxy.getWallet(request);
    }

    public void deposit(Integer userId, Integer walletId, BigDecimal amount) {
        CircuitBreaker circuitBreaker =circuitBreakerFactory.create("wallet-service");
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        TransactionRequest request = new TransactionRequest();
        request.setUserId(userId);
        request.setWalletId(walletId);
        request.setAmount(amount);

        circuitBreaker.run(
                () -> RequestContextPropagation.withContext(
                        requestAttributes,
                        () -> {
                            walletProxy.deposit(request);
                            return null;
                        }
                ),
                throwable -> {
                    System.out.println(
                            "Wallet service is unavailable."
                    );
                    return null;
                }
        );
    }

    public void withdrawal(Integer userId, Integer walletId, BigDecimal amount) {
        CircuitBreaker circuitBreaker =circuitBreakerFactory.create("wallet-service");
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        TransactionRequest request = new TransactionRequest();
        request.setUserId(userId);
        request.setWalletId(walletId);
        request.setAmount(amount);

        circuitBreaker.run(
                () -> RequestContextPropagation.withContext(
                        requestAttributes,
                        () -> {
                            walletProxy.withdrawal(request);
                            return null;
                        }
                ),
                throwable -> {
                    System.out.println(
                            "Wallet service is unavailable."
                    );
                    return null;
                }
        );
    }

    public List<TransactionInfo> history(Integer userId) {
        CircuitBreaker circuitBreaker =circuitBreakerFactory.create("wallet-service");
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        UserRequest request = new UserRequest();
        request.setUserId(userId);

        return circuitBreaker.run(
                () -> RequestContextPropagation.withContext(
                        requestAttributes,
                        () -> walletProxy.history(request)
                ),
                throwable -> {
                    System.out.println(
                            "Wallet service is unavailable."
                    );
                    return List.of();
                }
        );
    }
}