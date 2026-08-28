package com.wallet_service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class WalletService {
	
	private final WalletRepository walletRepo;
	
	public WalletService(WalletRepository walletRepo) {
		this.walletRepo=walletRepo;
	}
	
	public Wallet createWallet(User user) {
		Wallet wallet=new Wallet(BigDecimal.ZERO, user);
		return walletRepo.save(wallet);
	}
	
	public Optional<Wallet> findWallet(Integer id) {
		return walletRepo.findById(id);
	}
	
	public void deleteWallet(Integer id) {
		walletRepo.deleteById(id);
	}
	
	public void deleteWalletByUserId(Integer userId) {
	    Wallet wallet = walletRepo.findByUser_UserId(userId);
	    walletRepo.delete(wallet);
	}

}
