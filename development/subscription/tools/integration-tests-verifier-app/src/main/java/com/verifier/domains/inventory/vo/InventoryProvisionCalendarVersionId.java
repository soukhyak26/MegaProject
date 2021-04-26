package com.verifier.domains.inventory.vo;

import org.joda.time.LocalDate;

import java.io.Serializable;

public class InventoryProvisionCalendarVersionId implements Serializable{
    private String productId;
    private LocalDate startDate;
    private LocalDate endDate;

    public InventoryProvisionCalendarVersionId(String productId, LocalDate startDate, LocalDate endDate) {
        this.productId = productId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public InventoryProvisionCalendarVersionId() {
    }

    public String getProductId() {
        return productId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
