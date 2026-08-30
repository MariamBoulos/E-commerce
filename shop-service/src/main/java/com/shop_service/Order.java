package com.shop_service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name="orders")
public class Order {
	
	public Order() {
		
	}
	
	@Id
	@GeneratedValue
	private Integer orderId;
	
	private Integer userId;
	
	private BigDecimal total;
	
	private OrderStatus status;
	
	private OrderCurrency currency;
	
	@OneToMany(mappedBy = "order")
	@JsonManagedReference
	private List<OrderProduct> orderProducts = new ArrayList<>();
	
	private LocalDateTime intial;

	public Order(Integer orderId,Integer userId, BigDecimal total, OrderStatus status, OrderCurrency currency, LocalDateTime intial) {
		super();
		this.orderId = orderId;
		this.userId=userId;
		this.total = total;
		this.status = status;
		this.currency = currency;
		this.intial = intial;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public OrderCurrency getCurrency() {
		return currency;
	}

	public void setCurrency(OrderCurrency currency) {
		this.currency = currency;
	}

	public LocalDateTime getIntial() {
		return intial;
	}

	public void setIntial(LocalDateTime intial) {
		this.intial = intial;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", userId=" + userId + ", total=" + total + ", status=" + status
				+ ", currency=" + currency + ", orderProducts=" + orderProducts + ", intial=" + intial + "]";
	}

	public List<OrderProduct> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
