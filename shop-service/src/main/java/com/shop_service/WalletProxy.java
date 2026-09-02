package com.shop_service;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "wallet-service")
public interface WalletProxy {

    @PostMapping("/wallet")
    Optional<WalletInfo> getWallet(
            @RequestBody WalletRequest request);

    @PostMapping("/deposit")
    void deposit(
            @RequestBody TransactionRequest request);

    @PostMapping("/withdrawal")
    void withdrawal(
            @RequestBody TransactionRequest request);

    @PostMapping("/history")
    List<TransactionInfo> history(
            @RequestBody UserRequest request);
}