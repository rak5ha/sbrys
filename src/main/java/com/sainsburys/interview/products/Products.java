package com.sainsburys.interview.products;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rkotecha on 14/01/2016.
 */
public class Products {

    @JsonProperty("results")
    private List<Product> products;
    @JsonProperty("totalCost")
    private BigDecimal totalCost;

    public Products() {
        products = new ArrayList<Product>();
        totalCost = new BigDecimal(0.0);
    }

    public void addProduct(Product product) {
        products.add(product);
        totalCost = totalCost.add(product.getUnitPrice());
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
}
