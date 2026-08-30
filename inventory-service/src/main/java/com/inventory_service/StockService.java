package com.inventory_service;

import org.springframework.stereotype.Service;

@Service
public class StockService {
	
	private final StockRepository stockRepo;
	
	public StockService(StockRepository stockRepo) {
		super();
		this.stockRepo = stockRepo;
	}

	public void removeFromStock(Integer productId,Integer amount) {
		Stock stock = stockRepo.findByProductProductId(productId);
		 if (stock == null) {
		        throw new IllegalArgumentException("No stock record for product " + productId);
		    }
		    if (amount == null || amount <= 0) {
		        throw new IllegalArgumentException("Amount must be positive");
		    }
		    if (amount >= stock.getAvailable()) {
		        throw new InsufficientStockException(
		            "Cannot remove " + amount + " units — only " + stock.getAvailable() + " available");
		    }
		stock.setAvailable(stock.getAvailable()-amount);
		stockRepo.save(stock);
	}
	
	public void addToStock(Integer productId,Integer amount) {
		Stock stock = stockRepo.findByProductProductId(productId);
		stock.setAvailable(stock.getAvailable()+amount);
		stockRepo.save(stock);
	}
	
	public Stock getStock(Integer productId) {
		return stockRepo.findByProductProductId(productId);
	}

}
