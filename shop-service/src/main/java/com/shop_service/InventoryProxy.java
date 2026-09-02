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
	
	@PostMapping("/getProduct")
	public Optional<ProductInfo> getProduct(@RequestBody ProductRequest request);
	
	@PostMapping("/removeFromStock")
	public void removeFromStock(@RequestBody StockRequest request);
	
	@PostMapping("/addToStock")
	public void addToStock(@RequestBody StockRequest request);
	
	@PostMapping("/getStock")
	public StockInfo getStock(@RequestBody StockRequest request);

}