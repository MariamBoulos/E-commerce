package com.shop_service;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity(name="cart_product")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "product_id"}))
public class CartProduct {
	
	public CartProduct() {
		
	}
	
	@Id
	@GeneratedValue
	private Integer cartProductId;
	
	private Integer productId;
	
	private Integer quantity;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private Cart cart;

	public CartProduct(Integer cartProductId, Integer productId, Integer quantity, Cart cart) {
		super();
		this.cartProductId = cartProductId;
		this.productId = productId;
		this.quantity = quantity;
		this.cart = cart;
	}

	public Integer getCartProductId() {
		return cartProductId;
	}

	public void setCartProductId(Integer cartProductId) {
		this.cartProductId = cartProductId;
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

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "Cart_Product [cartProductId=" + cartProductId + ", productId=" + productId + ", quantity=" + quantity
				+ ", cart=" + cart + "]";
	}

}
