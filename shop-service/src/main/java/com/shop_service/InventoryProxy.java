package com.shop_service;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name="inventory-service")
public interface InventoryProxy {
	
	@PostMapping("/createProduct")
	public ProductInfo createProduct(@RequestBody ProductInfo product);
	
	@PostMapping("/addProductToCart/{userId}/{productId}/{quantity}")
	public void addProductToCart(@PathVariable Integer userId, @PathVariable Integer productId,
			@PathVariable Integer quantity);
	
	@GetMapping("/getProduct/{productId}")
	public Optional<ProductInfo> getProduct(@PathVariable("productId") Integer productId);
	
	@PostMapping("/removeFromStock/{productId}/{amount}")
	public void removeFromStock(@PathVariable("productId") Integer productId, @PathVariable("amount") Integer amount );
	
	@PostMapping("/addToStock/{productId}/{amount}")
	public void addToStock(@PathVariable("productId") Integer productId, @PathVariable("amount") Integer amount );
	
	@GetMapping("/getStock/{productId}")
	public StockInfo getStock(@PathVariable("productId") Integer productId);

}
