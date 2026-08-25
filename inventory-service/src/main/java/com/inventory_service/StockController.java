package com.inventory_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {
	
	@Autowired
	private final StockService stockService;

	public StockController(StockService stockService) {
		super();
		this.stockService = stockService;
	}
	
	@PostMapping("/removeFromStock/{productId}/{amount}")
	public void removeFromStock(@PathVariable Integer productId, @PathVariable Integer amount ) {
		stockService.removeFromStock(productId, amount);
	}
	
	@PostMapping("/addToStock/{productId}/{amount}")
	public void addToStock(@PathVariable Integer productId, @PathVariable Integer amount ) {
		stockService.addToStock(productId, amount);
	}

}
