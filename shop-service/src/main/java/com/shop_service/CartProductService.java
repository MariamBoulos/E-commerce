package com.shop_service;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class CartProductService {
	
	private final CartProductRepository cartProductRepo;
	private final CartRepository cartRepo;

	public CartProductService(CartProductRepository cartProductRepo,CartRepository cartRepo ) {
		super();
		this.cartProductRepo = cartProductRepo;
		this.cartRepo = cartRepo;
		
	}
	
	public CartProduct addProduct(Integer userId, Integer productId, Integer quantity) {
        Cart cart = cartRepo.findByUserId(userId).orElseThrow();
        Optional<CartProduct> existing =cartProductRepo.findByCartUserIdAndProductId(userId, productId);

        if (existing.isPresent()) {
            return addQuantity(existing.get().getCartProductId(), quantity);}
        
        CartProduct cartProduct = new CartProduct();
        cartProduct.setProductId(productId);
        cartProduct.setQuantity(quantity);
        cartProduct.setCart(cart);

        return cartProductRepo.save(cartProduct);
    }

	public CartProduct addQuantity(Integer cartProductId, Integer quantity) {
		 CartProduct cartProduct =cartProductRepo.findById(cartProductId).orElseThrow();
		    cartProduct.setQuantity(cartProduct.getQuantity() + quantity);
		    return cartProductRepo.save(cartProduct);
	}
	
	public void deleteProduct(Integer userId,Integer productId) {
		CartProduct cartProduct = cartProductRepo.findByCartUserIdAndProductId(userId, productId).orElseThrow();
	    cartProductRepo.delete(cartProduct);	
	    }

}
