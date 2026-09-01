package com.inventory_service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
	
	@Autowired
	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}
	
	@PostMapping("/addProductToCategory")
	public Category addProductToCategory(@RequestBody CategoryProductRequest request) {
	    return categoryService.addProductToCategory(
	        request.getCategoryId(),
	        request.getProductId()
	    );
	}
	
	@PutMapping("/updateCategory/{categoryId}")
	public Category updatCategory(@PathVariable Integer categoryId, @RequestBody Category category) {
		return categoryService.updateCategory(categoryId, category.getName());
	}
	
	@PostMapping("/createCategory")
	public Category createCategory(@RequestBody Category category) {
		return categoryService.createCategory(category);
	}
	
	@DeleteMapping("/removeProductFromCategory")
	public void removeProductFromCategory(@RequestBody CategoryProductRequest request) {
	    categoryService.removeProductFromCategory(
	        request.getCategoryId(),
	        request.getProductId()
	    );
	}
	
	@GetMapping("/categories")
	public List<Category> getAllCategories() {
	    return categoryService.getAllCategories();
	}

}
