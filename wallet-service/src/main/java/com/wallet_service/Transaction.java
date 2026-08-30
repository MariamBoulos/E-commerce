package com.wallet_service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@Entity(name="transaction")
public class Transaction {
	
	public Transaction(){	
		
	}
	
	@Id
	@GeneratedValue
	private Integer transactionId;
	
	private TransactionType type;
	
	private BigDecimal amount;
	
	private LocalDateTime timestamp;
	
	@ManyToOne
	@JoinColumn(name = "wallet_id", nullable = false)
	private Wallet wallet;

	public Transaction(BigDecimal amount, LocalDateTime timestamp
			, TransactionType type,Wallet wallet) {
		super();
		this.amount = amount;
		this.timestamp = timestamp;
		this.type = type;
		this.wallet = wallet;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", type=" + type + ", amount=" + amount + ", timestamp="
				+ timestamp + ", wallet=" + wallet + "]";
	}
	

}
