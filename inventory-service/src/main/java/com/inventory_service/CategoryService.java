package com.inventory_service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
        product.setCategory(category);
        category.getProducts().add(product);
		category.setLastUpdated(LocalDateTime.now());
        return categoryRepo.save(category);
    }
	
	public Category createCategory(Category category) {
		category.setCreated(LocalDate.now());
		category.setLastUpdated(LocalDateTime.now());
		return categoryRepo.save(category);
	}
	
	public Category updateCategory(Integer categoryId, String name) {
		Category category= categoryRepo.findById(categoryId).orElseThrow();
		category.setName(name);
		category.setLastUpdated(LocalDateTime.now());
		return categoryRepo.save(category);
	}
	
	public Category removeProductFromCategory(Integer categoryId, Integer productId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow();
        Product product = productRepo.findById(productId).orElseThrow();
        if (product.getCategory() == null
                || !product.getCategory().getCategoryId().equals(categoryId)) {
            throw new IllegalStateException("Product does not belong to this category");
        }
        product.setCategory(null);
        productRepo.save(product); 
        category.getProducts().remove(product);
		category.setLastUpdated(LocalDateTime.now());
        return categoryRepo.save(category);
    }
	
	public List<Category> getAllCategories() {
	    return categoryRepo.findAll();
	}
	
	
	

}
