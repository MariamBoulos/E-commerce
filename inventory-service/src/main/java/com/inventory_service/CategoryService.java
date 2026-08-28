package com.inventory_service;

import org.springframework.stereotype.Service;

@Service
public class CategoryService {
	
	private final CategoryRepository categoryRepo;
	private final ProductRepository productRepo;
	
	public CategoryService(CategoryRepository categoryRepo,ProductRepository productRepo) {
		super();
		this.categoryRepo = categoryRepo;
		this.productRepo = productRepo;
	}
	
	public Category addProductToCategory(Integer categoryId, Integer productId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow();
        Product product = productRepo.findById(productId).orElseThrow();
        category.getProducts().add(product);
        return categoryRepo.save(category);
    }
	
	
	

}
