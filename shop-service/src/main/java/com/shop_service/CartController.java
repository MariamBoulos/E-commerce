package com.shop_service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
	
	@Autowired
	private CartService cartService;

	public CartController(CartService cartService) {
		super();
		this.cartService = cartService;
	}
	
	@PostMapping("/cart")
	public Optional<Cart> getCartById(@RequestBody UserRequest request) {
	    return cartService.findByUserUserId(request.getUserId());
	}
	
	@PostMapping("/createCart")
	public Cart createCart(@RequestBody UserRequest request) {
	    return cartService.createCart(request.getUserId());
	}
	
	@DeleteMapping("/deleteCart")
	public void deleteCart(@RequestBody UserRequest request) {
	    cartService.deleteCartByUserId(request.getUserId());
	}
	
	

}
