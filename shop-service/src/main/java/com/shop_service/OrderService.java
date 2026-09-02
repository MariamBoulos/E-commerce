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
	
	public OrderService(OrderRepository orderRepo,OrderProductRepository orderProductRepo,
			CartProductRepository cartProductRepo,OrderProductService orderProductService,
			InventoryServiceClient inventoryServiceClient) {
		super();
		this.orderRepo = orderRepo;
		this.orderProductRepo = orderProductRepo;
		this.cartProductRepo = cartProductRepo;
		this.orderProductService = orderProductService;
		this.inventoryServiceClient = inventoryServiceClient;
	}

	public Order createOrder(Integer userId) {
	
	    if (orderRepo.existsByUserIdAndStatus(userId, OrderStatus.Delayed)) {
	        throw new RuntimeException(
	                "Cannot create a new order while you have a delayed order."
	        );
	    }
	
	    List<CartProduct> cartProducts =
	            cartProductRepo.findByCartUserId(userId);
	
	    if (cartProducts.isEmpty()) {
	        throw new RuntimeException("Cart is empty.");
	    }
	
	    BigDecimal total = BigDecimal.ZERO;
	
	    // Calculate total
	    for (CartProduct cartProduct : cartProducts) {
	
	        ProductInfo product =
	                inventoryServiceClient
	                        .getProduct(cartProduct.getProductId())
	                        .orElseThrow(() -> new RuntimeException(
	                                "Product not found: "
	                                + cartProduct.getProductId()
	                        ));
	
	        BigDecimal productTotal =
	                product.getPrice().multiply(
	                        BigDecimal.valueOf(cartProduct.getQuantity())
	                );
	
	        total = total.add(productTotal);
	    }
	
	    // Create order
	    Order order = new Order();
	    order.setUserId(userId);
	    order.setCurrency(OrderCurrency.EGP);
	    order.setTotal(total);
	    order.setIntial(LocalDateTime.now());
	    order.setStatus(OrderStatus.Delayed);
	
	    order = orderRepo.save(order);
	
	    // Remove the ordered quantity from inventory
	    for (CartProduct cartProduct : cartProducts) {
	
	        inventoryServiceClient.removeFromStock(
	                cartProduct.getProductId(),
	                cartProduct.getQuantity()
	        );
	
	        OrderProduct orderProduct = new OrderProduct();
	        orderProduct.setOrder(order);
	        orderProduct.setProductId(cartProduct.getProductId());
	        orderProduct.setQuantity(cartProduct.getQuantity());
	
	        orderProductRepo.save(orderProduct);
	    }
	
	    // Empty the cart
	    cartProductRepo.deleteAll(cartProducts);
	
	    return order;
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


