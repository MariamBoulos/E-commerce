package com.shop_service;

import java.util.Optional;

import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceClient {

    private final InventoryProxy inventoryProxy;
    private final CircuitBreakerFactory<?, ?> circuitBreakerFactory;

    public InventoryServiceClient(
            InventoryProxy inventoryProxy,
            CircuitBreakerFactory<?, ?> circuitBreakerFactory) {

        this.inventoryProxy = inventoryProxy;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    public ProductInfo createProduct(ProductInfo product) {

        CircuitBreaker circuitBreaker =
                circuitBreakerFactory.create("inventory-service");

        return circuitBreaker.run(
                () -> inventoryProxy.createProduct(product),
                throwable -> {
                    System.out.println("Inventory service is unavailable.");
                    return null;
                }
        );
    }

    public Optional<ProductInfo> getProduct(Integer productId) {

        CircuitBreaker circuitBreaker =
                circuitBreakerFactory.create("inventory-service");

        return circuitBreaker.run(
                () -> inventoryProxy.getProduct(productId),
                throwable -> {
                    System.out.println("Inventory service is unavailable.");
                    return Optional.empty();
                }
        );
    }

    public void removeFromStock(Integer productId, Integer amount) {

        CircuitBreaker circuitBreaker =
                circuitBreakerFactory.create("inventory-service");

        circuitBreaker.run(
                () -> {
                    inventoryProxy.removeFromStock(productId, amount);
                    return null;
                },
                throwable -> {
                    System.out.println("Inventory service is unavailable.");
                    return null;
                }
        );
    }
    
    public StockInfo getStock(Integer productId) {
        StockInfo stock = inventoryProxy.getStock(productId);

        System.out.println("STOCK FROM INVENTORY: " + stock);

        return stock;
    }

    public void addToStock(Integer productId, Integer amount) {

        CircuitBreaker circuitBreaker =
                circuitBreakerFactory.create("inventory-service");

        circuitBreaker.run(
                () -> {
                    inventoryProxy.addToStock(productId, amount);
                    return null;
                },
                throwable -> {
                    System.out.println("Inventory service is unavailable.");
                    return null;
                }
        );
    }
}