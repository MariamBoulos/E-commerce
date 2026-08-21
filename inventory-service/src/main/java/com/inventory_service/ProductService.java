package com.inventory_service;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
	private final ProductRepository productRepo;
	private final CategoryRepository categoryRepo;
	private final StockRepository stockRepo;

	public ProductService(ProductRepository productRepo, CategoryRepository categoryRepo, StockRepository stockRepo) {
		super();
		this.productRepo = productRepo;
		this.categoryRepo = categoryRepo;
		this.stockRepo = stockRepo;
	}

	public Product createProduct(Product product) {
	    Category category = categoryRepo.findById(product.getCategory().getCategoryId()).orElseThrow();
	    product.setCategory(category);
	    Product savedProduct = productRepo.save(product);

	    Stock stock = new Stock();
	    stock.setProduct(savedProduct);
	    stock.setAvailable(0);
	    stockRepo.save(stock);

	    return savedProduct;
	}
	
	public void deleteProduct(Integer productId) {

	    Product product = productRepo.findById(productId).orElseThrow();
	    Stock stock = stockRepo.findByProductProductId(productId).orElseThrow();
	    stockRepo.delete(stock);
	    productRepo.delete(product);
	}
	
	
}
