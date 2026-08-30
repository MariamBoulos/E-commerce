package com.inventory_service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ProductService {
	
	private final ProductRepository productRepo;
	private final CategoryRepository categoryRepo;
	private final StockRepository stockRepo;
	private final Path uploadPath = Paths.get("uploads/products");

	public ProductService(ProductRepository productRepo, CategoryRepository categoryRepo, StockRepository stockRepo) {
		super();
		this.productRepo = productRepo;
		this.categoryRepo = categoryRepo;
		this.stockRepo = stockRepo;
	}
	
	public Product createProduct(Product product, MultipartFile image) {
	    Category category = categoryRepo.findById(product.getCategory().getCategoryId()).orElseThrow();
	    product.setCategory(category);
		category.setLastUpdated(LocalDateTime.now());

	    try {
	    	Files.createDirectories(uploadPath);
	    	String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
	        Path filePath = uploadPath.resolve(fileName);
	        image.transferTo(filePath);
	        product.setImageUrl("/images/" + fileName);

	    } catch (IOException e) {
	        throw new RuntimeException("Could not save image", e);
	    }
	    
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
