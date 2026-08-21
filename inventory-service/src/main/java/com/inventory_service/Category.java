package com.inventory_service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name="category")
public class Category {
	
	protected Category() {
		
	}
	
	@Id
	@GeneratedValue
	private Integer categoryId;
	
	@OneToMany
	private List<Product> products = new ArrayList<>();
	
	@Column(unique = true)
	private String name;
	
	private LocalDate created;

	public Category(Integer categoryId, List<Product> products, String name, LocalDate created) {
		super();
		this.categoryId = categoryId;
		this.products = products;
		this.name = name;
		this.created = created;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getCreated() {
		return created;
	}

	public void setCreated(LocalDate created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", products=" + products + ", name=" + name + ", created="
				+ created + "]";
	}
	

}
