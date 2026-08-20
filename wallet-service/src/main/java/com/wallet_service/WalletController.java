package com.wallet_service;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {
	
	private WalletService service;

	public WalletController(WalletService service) {
		this.service = service;
	}
	
	@GetMapping("/wallet/{id}")
	public Optional<Wallet> getWallet(@PathVariable Integer id){
		return service.findWallet(id);
	}

}
