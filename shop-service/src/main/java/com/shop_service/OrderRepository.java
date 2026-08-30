package com.shop_service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	List<Order> findByUserId(Integer userId);
	Optional<Order> findByUserIdAndOrderId(Integer userId, Integer orderId);
	boolean existsByUserIdAndStatus(Integer userId, OrderStatus delayed);
}
