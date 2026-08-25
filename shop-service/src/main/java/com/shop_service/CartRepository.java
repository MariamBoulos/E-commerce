package com.shop_service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	
	Optional<Cart> findByUserId(Integer userId);

}
