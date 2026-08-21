package com.inventory_service;

import org.springframework.stereotype.Service;

@Service
public class CategoryService {
	
	private final CategoryRepository categoryRepo;
	
	public CategoryService(CategoryRepository categoryRepo) {
		super();
		this.categoryRepo = categoryRepo;
	}
	
	public Category addProductToCategory(Integer categoryId, Product product) {
        Category category = categoryRepo.findById(categoryId).orElseThrow();
        category.getProducts().add(product);
        return categoryRepo.save(category);
    }
	
	
	

}
