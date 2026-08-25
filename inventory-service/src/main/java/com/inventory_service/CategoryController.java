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
	
	@PostMapping("/addProductToCategory/{categoryId}/{product}")
	public Category addProductToCategory(@PathVariable Integer categoryId,@PathVariable Product product) {
		return categoryService.addProductToCategory(categoryId, product);
	}
	

}
