package com.sainsburys.interview.products;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;


/**
 * Created by rkotecha on 14/01/2016.
 */
public class Product {

    @JsonProperty("title")
    private String title;
    @JsonProperty("unit_price")
    private BigDecimal unitPrice;
    @JsonProperty("size")
    private String size;
    @JsonProperty("description")
    private String description;

    public Product(String title, String description, String size, BigDecimal unitPrice) {
        this.title = title;
        this.description = description;
        this.size = size;
        this.unitPrice = unitPrice;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public String getSize() {
        return size;
    }

    public String getDescription() {
        return description;
    }
}
