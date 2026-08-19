package com.wallet_service;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity(name="wallet")
public class Wallet {
	
	protected Wallet() {
		
    }
	
	@Id
	private Integer userId;

	private BigDecimal balance;
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "user_id", nullable = false)
    private User user;

	public Wallet(BigDecimal balance, User user) {
		super();
		this.balance = balance;
		this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Wallet [userId=" + userId + ", balance=" + balance + ", user=" + user + "]";
	}

}
