package com.shop_service;

public class StockInfo {

    private Integer stockId;
    private Integer available;
    private Integer productId;

    public StockInfo() {
    }

    public StockInfo(Integer stockId, Integer available, Integer productId) {
        this.stockId = stockId;
        this.available = available;
        this.productId = productId;
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "StockInfo [stockId=" + stockId
                + ", available=" + available
                + ", productId=" + productId + "]";
    }
}