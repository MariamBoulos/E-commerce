package com.shop_service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name="payment")
public class Payment {
	
	public Payment() {
		
	}
	
	@Id
	@GeneratedValue
	private Integer paymentId;
	
	private Integer userId;
	
	private PaymentStatus status;
	
	private BigDecimal amount;
	
	private LocalDateTime start;
	
	private LocalDateTime end;
	
	@OneToOne
	@JoinColumn(name = "order_id", nullable = false)
    private Order order;

	public Payment(Integer orderId, BigDecimal amount, Order order, PaymentStatus status) {
		super();
		this.status = status;
		this.amount = amount;
		this.order = order;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", userId=" + userId + ", status=" + status + ", amount=" + amount
				+ ", start=" + start + ", end=" + end + ", order=" + order + "]";
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

}
