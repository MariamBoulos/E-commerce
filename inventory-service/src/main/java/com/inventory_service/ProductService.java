package com.inventory_service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
		category.setLastUpdated(LocalDateTime.now());
	    
	    product.setCurrency(ProductCurrency.EGP);
	    Product savedProduct = productRepo.save(product);
	    Stock stock = new Stock();
	    stock.setProduct(savedProduct);
	    stock.setAvailable(0);
	    stockRepo.save(stock);
	    return savedProduct;
	}
	
	public Optional<Product> getProduct(Integer productId) {
		return productRepo.findById(productId);
	}
	
	
	public void deleteProduct(Integer productId) {
	    Product product = productRepo.findById(productId).orElseThrow();
	    Stock stock = stockRepo.findByProductProductId(productId);
	    stockRepo.delete(stock);
	    productRepo.delete(product);
	}
	
	public List<Product> getAllProducts() {
	    return productRepo.findAll();
	}
	
	public Product updateProduct(Integer productId, Product updatedProduct) {
	    Product product = productRepo.findById(productId).orElseThrow();
	    product.setDescription(updatedProduct.getDescription());
	    product.setPrice(updatedProduct.getPrice());
	    product.setCurrency(updatedProduct.getCurrency());
	    product.setCategory(updatedProduct.getCategory());
	    return productRepo.save(product);
	}
	
	
	
}
