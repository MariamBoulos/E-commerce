package com.shop_service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name="order")
public class Order {
	
	public Order() {
		
	}
	
	@Id
	@GeneratedValue
	private Integer orderId;
	
	private BigDecimal total;
	
	private OrderStatus status;
	
	private OrderCurrency currency;
	
	private List<OrderProduct> orderProducts;
	
	private LocalDateTime intial;

	public Order(Integer orderId, BigDecimal total, OrderStatus status, OrderCurrency currency, LocalDateTime intial) {
		super();
		this.orderId = orderId;
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
		return "Order [orderId=" + orderId + ", total=" + total + ", status=" + status + ", currency=" + currency
				+ ", intial=" + intial + "]";
	}

	public List<OrderProduct> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}

}
