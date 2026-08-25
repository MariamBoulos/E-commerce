package com.shop_service;

import java.math.BigDecimal;

public class WalletInfo {

    private Integer userId;
    private BigDecimal balance;

    public WalletInfo() {
    }

    public WalletInfo(Integer userId, BigDecimal balance) {
        this.userId = userId;
        this.balance = balance;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "WalletInfo [userId=" + userId + ", balance=" + balance + "]";
    }
}