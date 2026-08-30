package com.shop_service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;

	public PaymentController(PaymentService paymentService) {
		super();
		this.paymentService = paymentService;
	}
	
	@PostMapping("/payOrder/{userId}/{walletId}/{orderId}/{amount}")
	public void payOrder(@PathVariable Integer userId,@PathVariable Integer walletId,@PathVariable Integer orderId,
            @PathVariable BigDecimal amount) {
		paymentService.pay(userId, walletId, orderId, amount);
	}
	
	@PostMapping("/refundOrder/{orderId}/{amount}")
	public void refundOrder(@PathVariable Integer userId,@PathVariable Integer walletId,@PathVariable Integer orderId,
            @PathVariable BigDecimal amount) {
		paymentService.refund(userId, walletId, orderId, amount);
	}
}


