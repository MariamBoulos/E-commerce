package com.wallet_service;

public class CartInfo {

    private Integer cartId;
    private Integer userId;

    public CartInfo() {
    }

    public CartInfo(Integer cartId, Integer userId) {
        this.cartId = cartId;
        this.userId = userId;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CartInfo [cartId=" + cartId + ", userId=" + userId + "]";
    }
}