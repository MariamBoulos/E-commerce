package com.inventory_service;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name="stock")
public class Stock {
	
	public Stock() {
		
	}
	
	@Id
    @GeneratedValue
    private Integer stockId;
	
	private Integer available;
	
	@OneToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	public Stock(Integer stockId, Integer available, Product product) {
		super();
		this.stockId = stockId;
		this.available = available;
		this.product = product;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Stock [stockId=" + stockId + ", available=" + available + ", product=" + product + "]";
	}

}
