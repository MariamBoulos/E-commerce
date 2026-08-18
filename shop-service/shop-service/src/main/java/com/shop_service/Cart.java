package com.shop_service;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity(name="cart")
public class Cart {
	
	public Cart() {
		
	}
	
	@Id
	private Integer userId;

	public Cart(Integer userId) {
		super();
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Cart [userId=" + userId + "]";
	}

}
