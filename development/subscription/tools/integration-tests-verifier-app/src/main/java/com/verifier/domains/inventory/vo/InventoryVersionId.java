package com.verifier.domains.inventory.vo;

import org.joda.time.LocalDate;

import java.io.Serializable;

public class InventoryVersionId implements Serializable {
    private String productInventoryId;
    private String productId;
    private LocalDate demandChangeDate;

    public InventoryVersionId(String productInventoryId, String productId, LocalDate demandChangeDate) {
        this.productInventoryId = productInventoryId;
        this.productId = productId;
        this.demandChangeDate = demandChangeDate;
    }

    public InventoryVersionId(){}

    public String getProductInventoryId() {
        return productInventoryId;
    }

    public String getProductId() {
        return productId;
    }

    public LocalDate getDemandChangeDate() {
        return demandChangeDate;
    }
}
