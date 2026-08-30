package com.wallet_service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {
	
	@Autowired
	private WalletService walletService;
	
	public WalletController(WalletService walletService) {
		this.walletService = walletService;
	}


	@PostMapping("/wallet/{userId}")
	public Map<String, Object> createWallet(@PathVariable Integer userId) {

	    Wallet wallet = walletService.createWallet(userId);

	    return Map.of(
	        "walletId", wallet.getWalletId(),
	        "userId", wallet.getUser().getUserId(),
	        "balance", wallet.getBalance()
	    );
	}
	
	@GetMapping("/wallet/{walletId}")
	public Map<String, Object> getWallet(@PathVariable Integer walletId){
		Wallet wallet = walletService.findWallet(walletId).orElseThrow();
		Map<String, Object> result = new java.util.HashMap<>();
		result.put("walletId", wallet.getWalletId());
		result.put("userId", wallet.getUser().getUserId());
		result.put("balance", wallet.getBalance()); 
		return result;
	}
	
	@GetMapping("/wallets/{userId}")
	public List<Map<String, Object>> getAllWallets(@PathVariable Integer userId) {

	    return walletService.findAllWalletsByUserId(userId)
	            .stream()
	            .map(wallet -> {
	                Map<String, Object> result = new java.util.HashMap<>();
	                result.put("walletId", wallet.getWalletId());
	                result.put("userId", wallet.getUser().getUserId());
	                result.put("balance", wallet.getBalance());
	                return result;
	            })
	            .toList();
	}
	

}
