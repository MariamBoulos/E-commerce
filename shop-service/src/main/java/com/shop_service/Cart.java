package com.shop_service;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Entity(name="cart")
@Table(name = "cart", uniqueConstraints = {
		@UniqueConstraint(columnNames = "userId")})

public class Cart {
	
	public Cart() {
		
	}
	
	@Id
	@GeneratedValue
	private Integer cartId;
	
	@Column(nullable = false, unique = true)
	private Integer userId;

	public Cart(Integer userId,Integer cartId) {
		super();
		this.userId = userId;
		this.cartId=cartId;
	}
	
	public Cart(Integer userId) {
	    this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", userId=" + userId + "]";
	}

}
