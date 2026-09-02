package com.shop_service;

import java.util.Optional;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

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

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        return circuitBreaker.run(
                () -> RequestContextPropagation.withContext(
                        requestAttributes,
                        () -> inventoryProxy.createProduct(product)),
                throwable -> {
                    System.out.println("Inventory service is unavailable.");
                    return null;
                }
        );
    }

    public Optional<ProductInfo> getProduct(Integer productId) {

        CircuitBreaker circuitBreaker =
                circuitBreakerFactory.create("inventory-service");

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        ProductRequest request = new ProductRequest();
        request.setProductId(productId);

        return circuitBreaker.run(
                () -> RequestContextPropagation.withContext(
                        requestAttributes,
                        () -> inventoryProxy.getProduct(request)),
                throwable -> {
                    System.out.println("Inventory service is unavailable.");
                    return Optional.empty();
                }
        );
    }

    public void removeFromStock(Integer productId, Integer amount) {
        CircuitBreaker circuitBreaker =circuitBreakerFactory.create("inventory-service");
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        StockRequest request = new StockRequest();
        request.setProductId(productId);
        request.setAmount(amount);

        circuitBreaker.run(
                () -> RequestContextPropagation.withContext(requestAttributes, () -> {
                    inventoryProxy.removeFromStock(request);
                    return null;
                }),
                throwable -> {
                    System.out.println("Inventory service is unavailable.");
                    return null;
                }
        );
    }
    
    public StockInfo getStock(Integer productId) {
        StockRequest request = new StockRequest();
        request.setProductId(productId);

        StockInfo stock = inventoryProxy.getStock(request);

        System.out.println("STOCK FROM INVENTORY: " + stock);

        return stock;
    }
    
    public void addToStock(Integer productId, Integer amount) {
        CircuitBreaker circuitBreaker =circuitBreakerFactory.create("inventory-service");
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        StockRequest request = new StockRequest();
        request.setProductId(productId);
        request.setAmount(amount);

        circuitBreaker.run(
                () -> RequestContextPropagation.withContext(requestAttributes, () -> {
                    inventoryProxy.addToStock(request);
                    return null;
                }),
                throwable -> {
                    throwable.printStackTrace();
                    return null;
                }
        );
    
    }
}