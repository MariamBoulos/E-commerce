package com.wallet_service;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="wallet")
public class Wallet {
	
	protected Wallet() {
		
    }
	
	@Id
	@GeneratedValue
	private Integer walletId;
	
	private BigDecimal balance;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
    private User user;

	public Wallet(BigDecimal balance, User user) {
		super();
		this.balance = balance;
		this.user = user;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Wallet [walletId=" + walletId + ", balance=" + balance + ", user=" + user + "]";
	}

}
