package com.shop_service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {
	
	@Autowired
	 private final WalletProxy walletProxy;

	public PaymentService(WalletProxy walletProxy) {
		super();
		this.walletProxy = walletProxy;
	}

	public void pay(Integer orderId,BigDecimal amount) {
		Payment payment = new Payment();
		payment.setStart(LocalDateTime.now());
		walletProxy.withdrawal(amount, orderId);
		payment.setEnd(LocalDateTime.now());
	}
	
	public void refund(Integer orderId,BigDecimal amount) {
		walletProxy.deposit(amount, orderId);
	}
	
	
	
	

}
