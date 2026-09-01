package com.shop_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderProductController {
	
	@Autowired
	private OrderProductService orderProductService;

	public OrderProductController(OrderProductService orderProductService) {
		super();
		this.orderProductService = orderProductService;
	}
	
	@PostMapping("/addProductToOrder")
    public OrderProduct addProductToOrder(@RequestBody OrderProductRequest request) {
        return orderProductService.addToOrder(request.getOrderId(),request.getQuantity(),request.getCartProductId()
        );
    }

}
