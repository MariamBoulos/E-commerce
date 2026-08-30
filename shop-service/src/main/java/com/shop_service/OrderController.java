package com.shop_service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	public OrderController(OrderService orderService) {
		super();
		this.orderService = orderService;
	}
	
	@PostMapping("/createOrder/{userId}/{walletId}")
	public Order createOrder(@PathVariable Integer userId, @PathVariable Integer walletId) {
		return orderService.createOrder(userId,walletId);
	}
	
	@DeleteMapping("/deleteOrder/{userId}/{orderId}")
	public void deleteOrder(@PathVariable Integer userId,@PathVariable Integer orderId) {
		orderService.deleteOrder(userId, orderId);
	}
	
	@GetMapping("/getAllOrdersforUser/{userId}")
	public List<Order> getAllOrdersforUser(@PathVariable Integer userId){
		return orderService.allOrders(userId);
	}
	
	@GetMapping("/getOrder/{userId}/{orderId}")
	public Order getOrder(@PathVariable Integer userId,@PathVariable Integer orderId) {
	    return orderService.getOrder(userId, orderId);
	}

}
