package com.shop_service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionInfo {

    private Integer transactionId;
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private String type;

    public TransactionInfo() {
    }

    public TransactionInfo(Integer transactionId, BigDecimal amount,
                           LocalDateTime timestamp, String type) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.timestamp = timestamp;
        this.type = type;
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

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}