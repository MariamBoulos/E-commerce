package com.wallet_service;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {
 
	 List<Wallet> findByUser_UserId(Integer userId);
	}

