package com.shop_service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartProductController {
	
	@Autowired
	private CartProductService cartProductService;

	public CartProductController(CartProductService cartProductService) {
		super();
		this.cartProductService = cartProductService;
	}
	
	 @PostMapping("/addProductToCart")
	    public void addProductToCart(@RequestBody CartProductRequest request) {
	        cartProductService.addProduct(request.getUserId(), request.getProductId(),request.getQuantity()
	        );
	    }
	
	 @PostMapping("/addQuantity")
	    public void addQuantity(@RequestBody CartProductRequest request) {
	        cartProductService.addQuantity(request.getCartProductId(),request.getQuantity()
	        );
	    }
	
	 @PostMapping("/deleteProductInCart")
	    public void deleteProductInCart(@RequestBody CartProductRequest request) {
	        cartProductService.deleteProduct(request.getUserId(),request.getProductId()
	        );
	    }
	
	 @PostMapping("/cartProducts")
	    public List<CartProduct> getCartProducts(@RequestBody UserRequest request) {
	        return cartProductService.getCartProducts(request.getUserId());
	    }

	
	

}
