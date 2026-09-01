package com.wallet_service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {
	
	@Autowired
	private WalletService walletService;
	
	public WalletController(WalletService walletService) {
		this.walletService = walletService;
	}


	@PostMapping("/createWallet")
    public Map<String, Object> createWallet(@RequestBody WalletRequest request) {
        Wallet wallet = walletService.createWallet(request.getUserId());

        return Map.of(
            "walletId", wallet.getWalletId(),
            "userId", wallet.getUser().getUserId(),
            "balance", wallet.getBalance(),
            "created", wallet.getCreated()
        );
    }
	
	 @PostMapping("/wallet")
	    public Map<String, Object> getWallet(@RequestBody WalletRequest request) {
	        Wallet wallet = walletService.findWallet(request.getWalletId()).orElseThrow();
	        Map<String, Object> result = new java.util.HashMap<>();
	        result.put("walletId", wallet.getWalletId());
	        result.put("userId", wallet.getUser().getUserId());
	        result.put("balance", wallet.getBalance()); 
	        return result;
	    }
	
	 @PostMapping("/wallets")
	    public List<Map<String, Object>> getAllWallets(@RequestBody WalletRequest request) {
	        return walletService.findAllWalletsByUserId(request.getUserId())
	                .stream()
	                .map(wallet -> {
	                    Map<String, Object> result = new java.util.HashMap<>();
	                    result.put("walletId", wallet.getWalletId());
	                    result.put("userId", wallet.getUser().getUserId());
	                    result.put("balance", wallet.getBalance());
	                    result.put("created", wallet.getCreated());
	                    return result;
	                })
	                .toList();
	    }
	

}
