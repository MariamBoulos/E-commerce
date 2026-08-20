package com.wallet_service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TransactionService {
	
	    private WalletRepository walletRepo;
	    private TransactionRepository transactionRepo;

	    public TransactionService(WalletRepository walletRepo, TransactionRepository transactionRepo) {
	        this.walletRepo = walletRepo;
	        this.transactionRepo=transactionRepo;
	    }
	
	    public void deposit(Integer userId, BigDecimal amount) {
	        Wallet wallet = walletRepo.findByUserId(userId);
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
	    
	    public void withdraw(Integer userId, BigDecimal amount) {
	        Wallet wallet = walletRepo.findByUserId(userId);
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

	        return transactionRepo.findByWalletUserId(userId);
	    }
	

}
