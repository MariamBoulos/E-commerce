package com.shop_service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class CartProductService {
	
	private final CartProductRepository cartProductRepo;
	private final CartRepository cartRepo;
	private final InventoryServiceClient inventoryServiceClient;

	public CartProductService(CartProductRepository cartProductRepo,CartRepository cartRepo,InventoryServiceClient inventoryServiceClient ) {
		super();
		this.cartProductRepo = cartProductRepo;
		this.cartRepo = cartRepo;
		this.inventoryServiceClient = inventoryServiceClient;
		
	}
	
public CartProduct addProduct(Integer userId, Integer productId, Integer quantity) {

    Cart cart = cartRepo.findByUserId(userId).orElseThrow();

    StockInfo stock = inventoryServiceClient.getStock(productId);

    if (stock == null) {
        throw new IllegalStateException(
            "Could not retrieve stock information."
        );
    }

    if (quantity == null || quantity <= 0) {
        throw new IllegalArgumentException(
            "Quantity must be greater than 0."
        );
    }

    Optional<CartProduct> existing =
            cartProductRepo.findByCartUserIdAndProductId(userId, productId);

    int currentQuantity = 0;

    if (existing.isPresent()) {
        currentQuantity = existing.get().getQuantity();
    }

    int requestedQuantity = currentQuantity + quantity;

    if (stock.getAvailable() < requestedQuantity) {
        throw new InsufficientStockException(
            "Not enough stock."
        );
    }

    if (existing.isPresent()) {
        return addQuantity(
            existing.get().getCartProductId(),
            quantity
        );
    }

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
	
	public List<CartProduct> getCartProducts(Integer userId) {
	    return cartProductRepo.findByCartUserId(userId);
	}

}
