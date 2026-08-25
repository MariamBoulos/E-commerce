package com.wallet_service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {
 
	Wallet findByUserId(Integer userId);
	Optional<Wallet> findByUserUserId(Integer userId);
}

