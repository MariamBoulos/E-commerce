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
	
	@PostMapping("/addProductToCategory/{categoryId}/{productId}")
	public Category addProductToCategory(@PathVariable Integer categoryId,@PathVariable Integer productId) {
		return categoryService.addProductToCategory(categoryId, productId);
	}
	
	@PutMapping("/updateCategory/{categoryId}")
	public Category updatCategory(@PathVariable Integer categoryId, @RequestBody Category category) {
		return categoryService.updateCategory(categoryId, category.getName());
	}
	
	@PostMapping("/createCategory")
	public Category createCategory(@RequestBody Category category) {
		return categoryService.createCategory(category);
	}
	
	@DeleteMapping("/removeProductFromCategory/{categoryId}/{productId}")
	public void removeProductFromCategory(@PathVariable Integer categoryId,@PathVariable Integer productId){
		 categoryService.removeProductFromCategory(categoryId, productId);
	}
	
	@GetMapping("/categories")
	public List<Category> getAllCategories() {
	    return categoryService.getAllCategories();
	}

}
