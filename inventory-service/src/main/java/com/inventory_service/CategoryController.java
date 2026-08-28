package com.inventory_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
	
	@Autowired
	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}
	
	@PostMapping("/addProductToCategory/{categoryId}/{productId}")
	public Category addProductToCategory(@PathVariable Integer categoryId,@PathVariable Integer productId) {
		return categoryService.addProductToCategory(categoryId, productId);
	}
	

}
