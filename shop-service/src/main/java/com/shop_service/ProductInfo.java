package com.shop_service;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductInfo {

	private Integer productId;

	private String description;

	@JsonProperty("unitPrice")
	private BigDecimal price;

	public ProductInfo() {

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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductInfo [productId=" + productId + ", description=" + description + ", price=" + price + "]";
	}

}