package com.inventory_service;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	
	private BigDecimal unitPrice;
	
	private String imageUrl;
	
	private ProductCurrency currency;
	
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = true)
	@JsonBackReference
	private Category category;
	
	public Product(Integer productId, String description, BigDecimal unitPrice) {
		super();
		this.productId = productId;
		this.description = description;
		this.unitPrice = unitPrice;
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
		return unitPrice;
	}

	public void setPrice(BigDecimal price) {
		this.unitPrice = price;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", description=" + description + ", price=" + unitPrice + "]";
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public ProductCurrency getCurrency() {
		return currency;
	}

	public void setCurrency(ProductCurrency currency) {
		this.currency = currency;
	}

	

}
