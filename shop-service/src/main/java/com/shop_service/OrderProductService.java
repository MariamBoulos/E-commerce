package com.shop_service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class OrderProductService {
	
	private final OrderProductRepository orderProductRepo;
	private final OrderRepository orderRepo;
	private final CartProductRepository cartProductRepo;
	
	public OrderProductService(OrderProductRepository orderProductRepo,OrderRepository orderRepo,CartProductRepository cartProductRepo) {
		super();
		this.orderProductRepo = orderProductRepo;
		this.orderRepo = orderRepo;
		this.cartProductRepo = cartProductRepo;
	}
	
	public List<OrderProduct> findByOrder(Integer orderId) {
        return orderProductRepo.findByOrderOrderId(orderId);
    }

    public void deleteOrderProducts(Integer orderId) {
        List<OrderProduct> products =orderProductRepo.findByOrderOrderId(orderId);
        orderProductRepo.deleteAll(products);
    }

	public OrderProduct addToOrder(Integer orderId,Integer quantity, Integer cartProductId ) {
		CartProduct cartProduct = cartProductRepo.findById(cartProductId).orElseThrow();
		Order order= orderRepo.findById(orderId).orElseThrow();
		OrderProduct orderProduct=new OrderProduct();
		orderProduct.setOrder(order);
		orderProduct.setProductId(cartProduct.getProductId());
		orderProduct.setQuantity(quantity);
		return orderProductRepo.save(orderProduct);
	}
	
	

}
