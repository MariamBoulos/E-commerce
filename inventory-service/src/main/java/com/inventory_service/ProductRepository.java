package com.inventory_service;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	Product addBy(Integer amount,Integer productId);
	Product removeBy(Integer amount,Integer productId);

}
