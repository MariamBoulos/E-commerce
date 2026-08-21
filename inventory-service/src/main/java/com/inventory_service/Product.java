package com.inventory_service;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="product")
public class Product {
	
	public Product() {
		
	}
	
	@Id
	@GeneratedValue
	private Integer productId;
	
	private String description;
	
	private BigDecimal price;
	
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	public Product(Integer productId, String description, BigDecimal price) {
		super();
		this.productId = productId;
		this.description = description;
		this.price = price;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", description=" + description + ", price=" + price + "]";
	}

}
