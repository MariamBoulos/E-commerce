package com.inventory_service;

public class StockService {
	
	private final StockRepository stockRepo;
	
	public StockService(StockRepository stockRepo) {
		super();
		this.stockRepo = stockRepo;
	}

	public void removeFromStock(Integer productId,Integer amount) {
		Stock stock = stockRepo.findByProductProductId(productId).orElseThrow();
		stock.setAvailable(stock.getAvailable()-amount);
		stockRepo.save(stock);
	}
	
	public void addToStock(Integer productId,Integer amount) {
		Stock stock = stockRepo.findByProductProductId(productId).orElseThrow();
		stock.setAvailable(stock.getAvailable()+amount);
		stockRepo.save(stock);
	}

}
