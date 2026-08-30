package com.shop_service;

public class StockInfo {

    private Integer stockId;
    private Integer available;
    private ProductInfo product;

    public StockInfo() {
    }

    public StockInfo(Integer stockId, Integer available, ProductInfo product) {
        this.stockId = stockId;
        this.available = available;
        this.product = product;
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

    public ProductInfo getProduct() {
        return product;
    }

    public void setProduct(ProductInfo product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "StockInfo [stockId=" + stockId
                + ", available=" + available
                + ", product=" + product + "]";
    }
}