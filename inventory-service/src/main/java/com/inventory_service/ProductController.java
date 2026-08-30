package com.inventory_service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ProductController {
	
	@Autowired
	private final ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}
	
	
	@PostMapping(value = "/createProduct", consumes = "multipart/form-data")
	public Product createProduct(@RequestParam String description,@RequestParam BigDecimal price,
	        @RequestParam Integer categoryId,
	        @RequestParam("image") MultipartFile image) {

	    Product product = new Product();

	    product.setDescription(description);
	    product.setPrice(price);
	    Category category = new Category();
	    category.setCategoryId(categoryId);
	    product.setCategory(category);
	    return productService.createProduct(product, image);
	}
	
	@GetMapping("/getProduct/{productId}")
	public Optional<Product> getProduct(@PathVariable Integer productId) {
		return productService.getProduct(productId);
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
