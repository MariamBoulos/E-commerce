package com.wallet_service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
	
	public TransactionService service;
	
	public TransactionController(TransactionService service) {
		super();
		this.service = service;
	}

	@PostMapping("/deposit/{userId}/{walletId}/{amount}")
	public void deposit(@PathVariable Integer userId,@PathVariable Integer walletId ,@PathVariable BigDecimal amount) {
		service.deposit(userId, walletId,amount);
	}
	
	@PostMapping("/withdrawal/{userId}/{walletId}/{amount}")
	public void withdrawal(@PathVariable Integer userId,@PathVariable Integer walletId,@PathVariable BigDecimal amount) {
		service.withdraw(userId, walletId,amount);
	}
	
	@GetMapping("/history/{userId}")
	public List<Transaction> history(@PathVariable Integer userId){
		return service.getHistory(userId);
	}

}
