package com.shop_service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
	
	private final OrderRepository orderRepo;
	private final OrderProductRepository orderProductRepo;
	private final CartProductRepository cartProductRepo;
	private final OrderProductService orderProductService;
	private final InventoryServiceClient inventoryServiceClient;
	private final WalletServiceClient walletServiceClient;
	
	public OrderService(OrderRepository orderRepo,OrderProductRepository orderProductRepo,
			CartProductRepository cartProductRepo,OrderProductService orderProductService,
			InventoryServiceClient inventoryServiceClient,WalletServiceClient walletServiceClient) {
		super();
		this.orderRepo = orderRepo;
		this.orderProductRepo = orderProductRepo;
		this.cartProductRepo = cartProductRepo;
		this.orderProductService = orderProductService;
		this.inventoryServiceClient = inventoryServiceClient;
		this.walletServiceClient = walletServiceClient;
	}

	public Order createOrder(Integer userId, Integer walletId) {
		if (orderRepo.existsByUserIdAndStatus(userId, OrderStatus.Delayed)) {
	        throw new RuntimeException(
	                "Cannot create a new order while you have a delayed order."
	        );
	    }
	    List<CartProduct> cartProducts =cartProductRepo.findByCartUserId(userId);

	    if (cartProducts.isEmpty()) {
	        throw new RuntimeException("Cart is empty.");
	    }

	    BigDecimal total = BigDecimal.ZERO;

	    // Calculate total
	    for (CartProduct cartProduct : cartProducts) {

	    	ProductInfo product =inventoryServiceClient.getProduct(cartProduct.getProductId()).orElseThrow(() ->
	    	                new RuntimeException(
	    	                    "Product not found: " + cartProduct.getProductId()
	    	                )
	    	            );

	        BigDecimal productTotal =
	                product.getPrice().multiply(
	                    BigDecimal.valueOf(cartProduct.getQuantity())
	                );

	        total = total.add(productTotal);
	    }

	    // Get wallet
	    WalletInfo wallet = walletServiceClient.getWallet(walletId)
	            .orElseThrow(() -> new RuntimeException("Wallet not found"));
	    
	    System.out.println("Wallet balance: " + wallet.getBalance());
	    System.out.println("Order total: " + total);
	    
	    // Check balance
	    if (wallet.getBalance().compareTo(total) < 0) {
	        throw new RuntimeException("Insufficient balance.");
	    }

	    // Create order
	    Order order = new Order();
	    order.setUserId(userId);
	    order.setCurrency(OrderCurrency.EGP);
	    order.setTotal(total);
	    order.setIntial(LocalDateTime.now());
	    order = orderRepo.save(order);

	    // Remove products from inventory
	    for (CartProduct cartProduct : cartProducts) {
	        inventoryServiceClient.removeFromStock(
	                cartProduct.getProductId(),
	                cartProduct.getQuantity()
	        );

	        OrderProduct orderProduct = new OrderProduct();
	        orderProduct.setOrder(order);
	        orderProduct.setProductId(cartProduct.getProductId());
	        orderProduct.setQuantity(cartProduct.getQuantity());
	        order.getOrderProducts().add(orderProduct);
	        orderProductRepo.save(orderProduct);
	    }

	    walletServiceClient.withdrawal(userId, walletId, total);
	    order.setStatus(OrderStatus.Confirmed);
	    return orderRepo.save(order);
	}
	
	public void deleteOrder(Integer userId,Integer orderId) {
		Order order = orderRepo.findByUserIdAndOrderId(userId, orderId).orElseThrow();
		orderProductService.deleteOrderProducts(orderId);
		inventoryServiceClient.addToStock(userId, orderId);
	    orderRepo.delete(order);		
	}
	
	public List<Order> allOrders(Integer userId){
		 return orderRepo.findByUserId(userId);
	}
	
	public Order getOrder(Integer userId, Integer orderId) {
	    return orderRepo.findByUserIdAndOrderId(userId, orderId).orElseThrow();
	}
	
}


