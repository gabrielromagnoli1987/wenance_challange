package com.wenance.challange.repositories.result;

import java.math.BigDecimal;

public class AverageAndMaxPrice {
    private BigDecimal averagePrice;
    private BigDecimal maxPrice;

    public AverageAndMaxPrice() {
    }

    public AverageAndMaxPrice(BigDecimal averagePrice, BigDecimal maxPrice) {
        this.averagePrice = averagePrice;
        this.maxPrice = maxPrice;
    }

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }
}
