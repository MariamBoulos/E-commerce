package com.wallet_service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TransactionService {
	
	    private final WalletRepository walletRepo;
	    private final TransactionRepository transactionRepo;

	    public TransactionService(WalletRepository walletRepo, TransactionRepository transactionRepo) {
	        this.walletRepo = walletRepo;
	        this.transactionRepo=transactionRepo;
	    }
	
	    public void deposit(Integer walletId, BigDecimal amount) {
	    	Wallet wallet = walletRepo.findById(walletId).orElseThrow(() -> new RuntimeException("Wallet not found"));
	        BigDecimal balance = wallet.getBalance();
	        balance = balance.add(amount);
	        wallet.setBalance(balance);
	        walletRepo.save(wallet);
	        
	        Transaction transaction = new Transaction(
	                amount,
	                LocalDateTime.now(),
	                TransactionType.DEPOSIT,
	                wallet);
	        
	        transactionRepo.save(transaction);
	    }
	    
	    public void withdraw(Integer walletId, BigDecimal amount) {
	    	Wallet wallet = walletRepo.findById(walletId).orElseThrow(() -> new RuntimeException("Wallet not found"));
	        BigDecimal balance = wallet.getBalance();
	        if (balance.compareTo(amount) < 0) {
	            throw new RuntimeException("Insufficient balance");
	        }
	        balance = balance.subtract(amount);
	        wallet.setBalance(balance);
	        walletRepo.save(wallet);
	        
	        Transaction transaction = new Transaction(
	                amount,
	                LocalDateTime.now(),
	                TransactionType.WITHDRAWAL,
	                wallet);
	        
	        transactionRepo.save(transaction);
	    }
	    
	    public List<Transaction> getHistory(Integer userId) {

	        return transactionRepo.findByWallet_User_UserId(userId);
	    }
	

}
