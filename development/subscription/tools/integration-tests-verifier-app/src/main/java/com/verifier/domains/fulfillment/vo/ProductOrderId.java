package com.verifier.domains.fulfillment.vo;

import org.joda.time.LocalDate;

import java.io.Serializable;


public class ProductOrderId implements Serializable {
    private String productId;
    private LocalDate orderDate;

    public ProductOrderId(String productId, LocalDate orderDate) {
        this.productId = productId;
        this.orderDate = orderDate;
    }

    public ProductOrderId(){}

    public String getProductId() {
        return productId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }
}
