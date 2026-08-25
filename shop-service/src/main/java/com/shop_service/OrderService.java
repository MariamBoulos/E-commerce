package com.shop_service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
	
	private final OrderRepository orderRepo;
	private final OrderProductRepository orderProductRepo;
	private final CartProductRepository cartProductRepo;
	private final OrderProductService orderProductService;
	private final InventoryProxy inventoryProxy;
	
	public OrderService(OrderRepository orderRepo,OrderProductRepository orderProductRepo,
			CartProductRepository cartProductRepo,OrderProductService orderProductService,
			InventoryProxy inventoryProxy) {
		super();
		this.orderRepo = orderRepo;
		this.orderProductRepo = orderProductRepo;
		this.cartProductRepo = cartProductRepo;
		this.orderProductService = orderProductService;
		this.inventoryProxy = inventoryProxy;
	}

	public Order createOrder(Integer userId) {
		Order order1 = new Order();
		List<CartProduct> cartProducts = cartProductRepo.findByCartUserId(userId);
		
		 for (int i = 0; i < cartProducts.size(); i++) {
		        CartProduct cartProduct = cartProducts.get(i);
		        OrderProduct orderProduct = new OrderProduct();
		        orderProduct.setOrder(order1);
		        orderProduct.setProductId(cartProduct.getProductId());
		        orderProduct.setQuantity(cartProduct.getQuantity());
		        inventoryProxy.removeFromStock(userId, userId);
		        orderProductRepo.save(orderProduct);
		}
		
		return orderRepo.save(order1);
	}
	
	public void deleteOrder(Integer userId,Integer orderId) {
		Order order = orderRepo.findByUserIdAndOrderId(userId, orderId).orElseThrow();
		orderProductService.deleteOrderProducts(orderId);
		inventoryProxy.addToStock(userId, orderId);
	    orderRepo.delete(order);		
	}
	
	public List<Order> allOrders(Integer userId){
		 return orderRepo.findByUserId(userId);
	}
}


