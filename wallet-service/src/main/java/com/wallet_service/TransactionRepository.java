package com.wallet_service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	 List<Transaction> findByWalletUserId(Integer userId);
}
