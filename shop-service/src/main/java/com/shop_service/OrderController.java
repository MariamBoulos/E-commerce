package com.shop_service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	public OrderController(OrderService orderService) {
		super();
		this.orderService = orderService;
	}
	
	@PostMapping("/createOrder")
    public Order createOrder(@RequestBody OrderRequest request) {
        return orderService.createOrder(request.getUserId());
    }
	
	@PostMapping("/deleteOrder")
    public void deleteOrder(@RequestBody OrderRequest request) {
        orderService.deleteOrder(request.getUserId(),request.getOrderId()
        );
    }
	
	 @PostMapping("/getAllOrdersforUser")
	    public List<Order> getAllOrdersforUser(@RequestBody OrderRequest request) {
	        return orderService.allOrders(request.getUserId());
	    }
	
	 @PostMapping("/getOrder")
	    public Order getOrder(@RequestBody OrderRequest request) {
	        return orderService.getOrder(request.getUserId(), request.getOrderId()
	        );
	    }

}
