package com.shop_service;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class CartService {
	
	private final CartRepository cartRepo;

	public CartService(CartRepository cartRepo) {
		super();
		this.cartRepo = cartRepo;
	}
	
	public Cart createCart(Integer userId) {
		Cart cart1=new Cart(userId,null);
		return cartRepo.save(cart1);
	}
	
	public void deleteCartByUserId(Integer userId) {
	    Cart cart = cartRepo.findByUserId(userId).orElseThrow();
	    cartRepo.delete(cart);
	}
	
	public Optional<Cart> findByUserUserId(Integer userId){
		return cartRepo.findByUserId(userId);
	}
	
	

}
