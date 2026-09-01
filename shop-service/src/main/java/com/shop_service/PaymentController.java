package com.shop_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;

	public PaymentController(PaymentService paymentService) {
		super();
		this.paymentService = paymentService;
	}
	
	@PostMapping("/payOrder")
    public void payOrder(@RequestBody PaymentRequest request) {
        paymentService.pay(request.getUserId(),request.getWalletId(),request.getOrderId(),request.getAmount()
        );
    }
	
	@PostMapping("/refundOrder")
    public void refundOrder(@RequestBody PaymentRequest request) {
        paymentService.refund(request.getUserId(),request.getWalletId(),request.getOrderId(),request.getAmount()
        );
    }
}


