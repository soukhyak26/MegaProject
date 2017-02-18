package com.affaince.subscription.business.command;

import org.joda.time.LocalDateTime;

public class ChangePurchaseProvisionPerProductCommand {
    private Integer id;
    private String productId;
    private double provisionAdjustment;

    public ChangePurchaseProvisionPerProductCommand(Integer id, String productId,double provisionAdjustment) {
        this.id = id;
        this.productId=productId;
        this.provisionAdjustment = provisionAdjustment;
    }

    public Integer getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public double getProvisionAdjustment() {
        return provisionAdjustment;
    }
}
