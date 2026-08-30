package com.shop_service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartProductController {
	
	@Autowired
	private CartProductService cartProductService;

	public CartProductController(CartProductService cartProductService) {
		super();
		this.cartProductService = cartProductService;
	}
	
	@PostMapping("/addProductToCart/{userId}/{productId}/{quantity}")
	public void addProductToCart(@PathVariable Integer userId, @PathVariable Integer productId,
			@PathVariable Integer quantity) {
		cartProductService.addProduct(userId, productId, quantity);
	}
	
	@PostMapping("/addQuantity/{cartProductId}/{quantity}")
	public void addQuantity(@PathVariable Integer cartProductId,@PathVariable Integer quantity) {
		cartProductService.addQuantity(cartProductId, quantity);
	}
	
	@DeleteMapping("/deleteProductInCart/{id}/{productId}")
	public void deleteProductInCart(@PathVariable Integer userId,@PathVariable Integer productId) {
		cartProductService.deleteProduct(userId, productId);
	}
	
	@GetMapping("/cartProducts/{userId}")
	public List<CartProduct> getCartProducts(@PathVariable Integer userId) {
	    return cartProductService.getCartProducts(userId);
	}
	
	

}
