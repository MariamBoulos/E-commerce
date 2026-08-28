package com.shop_service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {
	
	@Autowired
	private final WalletServiceClient walletServiceClient;
	
	public PaymentService(WalletServiceClient walletServiceClient) {
		super();
		this.walletServiceClient = walletServiceClient;

	}

	public void pay(Integer orderId,BigDecimal amount) {
		Payment payment = new Payment();
		payment.setStart(LocalDateTime.now());
		walletServiceClient.withdrawal(amount, orderId);
		payment.setEnd(LocalDateTime.now());
	}
	
	public void refund(Integer orderId,BigDecimal amount) {
		walletServiceClient.deposit(amount, orderId);
	}
	
	
	
	

}
