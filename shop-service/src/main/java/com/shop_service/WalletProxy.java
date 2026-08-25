package com.shop_service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name="wallet-service",url="http://localhost:8100")
public interface WalletProxy {
	
	@GetMapping("/wallet/{id}")
	public Optional<WalletInfo> getWallet(@PathVariable Integer id);
	
	@PostMapping("/deposit/{id}/{amount}")
	public void deposit(@PathVariable BigDecimal amount, @PathVariable Integer id);
	
	@PostMapping("/withdrawal/{id}/{amount}")
	public void withdrawal(@PathVariable BigDecimal amount, @PathVariable Integer id);
	
	@GetMapping("/history/{id}")
	public List<TransactionInfo> history(@PathVariable Integer id);
	
	}


