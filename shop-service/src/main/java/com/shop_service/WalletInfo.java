package com.shop_service;

import java.math.BigDecimal;

public class WalletInfo {

    private Integer userId;
    private BigDecimal balance;
    private Integer walletId;

    public WalletInfo() {
    }

    public WalletInfo(Integer walletId, Integer userId, BigDecimal balance) {
        this.walletId = walletId;
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
    
    public Integer getWalletId() {
        return walletId;
    }

    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }

    @Override
	public String toString() {
		return "WalletInfo [userId=" + userId + ", balance=" + balance + ", walletId=" + walletId + "]";
	}
}