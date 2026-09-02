package com.wallet_service;

import java.util.Optional;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "shop-service")
public interface ShopProxy {

    @PostMapping("/cart")
    Optional<CartInfo> getCartById(@RequestBody UserRequest request);

    @PostMapping("/createCart")
    CartInfo createCart(@RequestHeader("X-Auth-User") String authUser,
    		@RequestBody UserRequest request);

    @DeleteMapping("/deleteCart")
    void deleteCartByUserId(@RequestBody UserRequest request);
}