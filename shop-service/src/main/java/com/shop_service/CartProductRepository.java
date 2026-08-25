package com.shop_service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductRepository extends JpaRepository<CartProduct, Integer> {
	
	Optional<CartProduct> findByCartUserIdAndProductId(Integer userId,Integer productId);

	List<CartProduct> findByCartUserId(Integer userId);

}
