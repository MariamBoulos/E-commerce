package com.shop_service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
	
	@Autowired
	private CartService cartService;

	public CartController(CartService cartService) {
		super();
		this.cartService = cartService;
	}
	
	@GetMapping("/cart/{id}")
	public Optional<Cart> getCartById(@PathVariable Integer userId) {
		return cartService.findByUserUserId(userId);
	}
	
	@PostMapping("/createCart/{id}")
	public Cart createCart(@PathVariable Integer userId) {
		return cartService.createCart(userId);
	}
	
	@DeleteMapping("/deleteCart/{id}")
	public void deleteCart(@PathVariable Integer userId) {
		cartService.deleteCartByUserId(userId);
	}
	
	

}
