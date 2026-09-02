package com.inventory_service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
	
	@Autowired
	private final ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}
	
	
	@PostMapping("/createProduct")
	public Product createProduct(@RequestBody Product product) {
	    return productService.createProduct(product);
	}
	
	@PostMapping("/getProduct")
	public Optional<Product> getProduct(@RequestBody ProductRequest request) {
	    return productService.getProduct(request.getProductId());
	}
	
	@DeleteMapping("/deleteProduct/{productId}")
	public void deleteProduct(@PathVariable Integer productId) {
		productService.deleteProduct(productId);
	}
	
	@GetMapping("/products")
	public List<Product> getAllProducts() {
	    return productService.getAllProducts();
	}
	
	@PutMapping("/updateProduct/{id}")
	public Product updateProduct(@PathVariable Integer id, @RequestBody Product product) {
	    return productService.updateProduct(id, product);
	}
	
	

}
