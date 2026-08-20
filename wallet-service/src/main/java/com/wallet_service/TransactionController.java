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

	@PostMapping("/deposit/{id}/{amount}")
	public void deposit(@PathVariable BigDecimal amount, @PathVariable Integer id) {
		service.deposit(id, amount);
	}
	
	@PostMapping("/withdrawal/{id}/{amount}")
	public void withdrawal(@PathVariable BigDecimal amount, @PathVariable Integer id) {
		service.withdraw(id, amount);
	}
	
	@GetMapping("/history/{id}")
	public List<Transaction> history(@PathVariable Integer id){
		return service.getHistory(id);
	}

}
