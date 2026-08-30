package com.shop_service;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity(name="order_product")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"order_id", "product_id"}))
public class OrderProduct {
	
	public OrderProduct() {
		
	}
	
	@Id
	@GeneratedValue
	private Integer orderProductId;
	
	private Integer productId;
	
	private Integer quantity;
	
	private BigDecimal price;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	@JsonBackReference
	private Order order;

	public OrderProduct(Integer orderProductId, Integer productId, Integer quantity, BigDecimal price, Order order) {
		super();
		this.orderProductId = orderProductId;
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
		this.order = order;
	}

	public Integer getOrderProductId() {
		return orderProductId;
	}

	public void setOrderProductId(Integer orderProductId) {
		this.orderProductId = orderProductId;
	}
	

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Order_Product [orderProductId=" + orderProductId + ", productId=" + productId + ", quantity=" + quantity
				+ ", price=" + price + ", order=" + order + "]";
	}

}
