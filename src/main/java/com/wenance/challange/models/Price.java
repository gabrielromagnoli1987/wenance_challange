package com.wenance.challange.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Document(collation = "Prices")
public class Price {
    // {"lprice":"21385.3","curr1":"BTC","curr2":"USD"}
    @Id
    private Integer id;
    private BigDecimal lprice;
    private String curr1;
    private String curr2;
    private Instant timestamp;

    public Price() {

    }

    public Price(BigDecimal lprice, String curr1, String curr2, Instant timestamp) {
        this.lprice = lprice;
        this.curr1 = curr1;
        this.curr2 = curr2;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getLprice() {
        return lprice;
    }

    public void setLprice(BigDecimal lprice) {
        this.lprice = lprice;
    }

    public String getCurr1() {
        return curr1;
    }

    public void setCurr1(String curr1) {
        this.curr1 = curr1;
    }

    public String getCurr2() {
        return curr2;
    }

    public void setCurr2(String curr2) {
        this.curr2 = curr2;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
