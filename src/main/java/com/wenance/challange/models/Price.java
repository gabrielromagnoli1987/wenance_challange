package com.wenance.challange.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;
import java.time.Instant;

@Document("Prices")
public class Price {

    @Id
    private String id;

    @Field(targetType = FieldType.DECIMAL128)
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
