package com.wallet_service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class WalletService {
	
	private final WalletRepository walletRepo;
	 private final UserRepository userRepo;
	
	public WalletService(WalletRepository walletRepo, UserRepository userRepo) {
		this.walletRepo=walletRepo;
		this.userRepo = userRepo;
		
	}
	
	public Wallet createWallet(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Wallet wallet = new Wallet(BigDecimal.ZERO, user);
        return walletRepo.save(wallet);
	}
	
	public Optional<Wallet> findWallet(Integer walletId) {
		return walletRepo.findById(walletId);
	}
	
	public List<Wallet> findAllWalletsByUserId(Integer userId) {
	    return walletRepo.findByUser_UserId(userId);
	}
	
	public void deleteWallet(Integer walletId) {
		walletRepo.deleteById(walletId);
	}
	
	public void deleteAllWalletsByUserId(Integer userId) {
	    List<Wallet> wallets = walletRepo.findByUser_UserId(userId);
	    walletRepo.deleteAll(wallets);
	}
	

}
