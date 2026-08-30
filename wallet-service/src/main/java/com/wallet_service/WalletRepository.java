package com.wallet_service;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {
 
	Optional<Wallet> findByWalletIdAndUserUserId(Integer walletId,Integer userId);
	List<Wallet> findByUser_UserId(Integer userId);
	}

