package com.shop_service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final WalletServiceClient walletServiceClient;
    private final OrderRepository orderRepo;

    public PaymentService(WalletServiceClient walletServiceClient,OrderRepository orderRepo) {
        this.walletServiceClient = walletServiceClient;
        this.orderRepo = orderRepo;
    }

    public Order payOrder(Integer userId, Integer orderId, Integer walletId) {
        Order order = orderRepo.findByUserIdAndOrderId(userId, orderId)
                .orElseThrow(() -> new RuntimeException("Order not found."));

        if (order.getStatus() != OrderStatus.Delayed) {
            throw new RuntimeException("Order is not awaiting payment.");
        }

        WalletInfo wallet = walletServiceClient.getWallet(walletId)
                .orElseThrow(() -> new RuntimeException("Wallet not found."));

        if (wallet.getBalance().compareTo(order.getTotal()) < 0) {
            throw new RuntimeException("Insufficient balance.");
        }

        walletServiceClient.withdrawal(userId,walletId,order.getTotal());
        order.setStatus(OrderStatus.Confirmed);
        return orderRepo.save(order);
    }

    public void refund(Integer userId,Integer walletId,Integer orderId,BigDecimal amount) {
        walletServiceClient.deposit(userId, walletId, amount);
    }
}