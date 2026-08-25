package com.shop_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderProductController {
	
	@Autowired
	private OrderProductService orderProductService;

	public OrderProductController(OrderProductService orderProductService) {
		super();
		this.orderProductService = orderProductService;
	}
	
	@PostMapping("/addProductToOrder/{orderId}/{quantity}/{cartProductId")
	public OrderProduct addProductToOrder(@PathVariable Integer orderId,@PathVariable Integer quantity,@PathVariable Integer cartProductId) {
		return orderProductService.addToOrder(orderId, quantity, cartProductId);
	}
	

}
