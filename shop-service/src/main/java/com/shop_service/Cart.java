package com.shop_service;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity(name="cart")
public class Cart {
	
	public Cart() {
		
	}
	
	@Id
	@GeneratedValue
	private Integer cartId;
	
	private Integer userId;

	public Cart(Integer userId,Integer cartId) {
		super();
		this.userId = userId;
		this.cartId=cartId;
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
