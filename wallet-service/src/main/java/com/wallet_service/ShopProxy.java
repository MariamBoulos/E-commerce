package com.wallet_service;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@FeignClient(name="shop-service",url="http://localhost:8200")
public interface ShopProxy {
	
	@GetMapping("/cart/{id}")
	public Optional<CartInfo> getCartById(@PathVariable Integer userId);
	
	@PostMapping("/createCart/{id}")
	public CartInfo createCart(@PathVariable Integer userId);
	
	@DeleteMapping("/deleteCart/{id}")
	public void deleteCartByUserId(@PathVariable Integer userId);
	
	

}
