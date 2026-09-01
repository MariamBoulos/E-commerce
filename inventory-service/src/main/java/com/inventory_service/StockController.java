package com.inventory_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {
	
	@Autowired
	private final StockService stockService;

	public StockController(StockService stockService) {
		super();
		this.stockService = stockService;
	}
	
	@PostMapping("/removeFromStock")
	public void removeFromStock(@RequestBody StockRequest request) {
	    stockService.removeFromStock(request.getProductId(), request.getAmount());
	}
	
	@PostMapping("/addToStock")
	public void addToStock(@RequestBody StockRequest request) {
	    stockService.addToStock(request.getProductId(), request.getAmount());
	}
	
	@PostMapping("/getStock")
	public Stock getStock(@RequestBody StockRequest request) {
	    return stockService.getStock(request.getProductId());
	}

}
