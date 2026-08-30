package com.shop_service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name="wallet-service")
public interface WalletProxy {
	
	@GetMapping("/wallet/{walletId}")
	public Optional<WalletInfo> getWallet(@PathVariable Integer walletId);
	
	@PostMapping("/deposit/{userId}/{walletId}/{amount}")
	public void deposit( @PathVariable Integer userId,@PathVariable Integer walletId,@PathVariable BigDecimal amount);
	
	@PostMapping("/withdrawal/{userId}/{walletId}/{amount}")
	public void withdrawal(@PathVariable Integer userId,@PathVariable Integer walletId, @PathVariable BigDecimal amount);
	
	@GetMapping("/history/{userId}")
	public List<TransactionInfo> history(@PathVariable Integer userId);
	
	}


