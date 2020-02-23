package com.verifier.domains.fulfillment.vo;

import org.joda.time.LocalDate;

import java.io.Serializable;

public class ProductInventoryUpdateId implements Serializable {
    private String productId;
    private LocalDate inventoryUpdateDate;


    public ProductInventoryUpdateId(String productId, LocalDate inventoryUpdateDate) {
        this.productId = productId;
        this.inventoryUpdateDate = inventoryUpdateDate;
    }

    public ProductInventoryUpdateId(){}

    public String getProductId() {
        return productId;
    }

    public LocalDate getInventoryUpdateDate() {
        return inventoryUpdateDate;
    }
}
