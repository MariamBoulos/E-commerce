package com.inventory_service;


import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Integer> {
	
	Stock findByProductProductId(Integer productId);

}
