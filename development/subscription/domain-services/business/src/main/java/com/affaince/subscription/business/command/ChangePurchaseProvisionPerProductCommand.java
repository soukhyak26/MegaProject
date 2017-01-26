package com.affaince.subscription.business.command;

import org.joda.time.LocalDateTime;

public class ChangePurchaseProvisionPerProductCommand {
    private Integer id;
    private double provisionAdjustment;

    public ChangePurchaseProvisionPerProductCommand(Integer id, double provisionAdjustment) {
        this.id = id;
        this.provisionAdjustment = provisionAdjustment;
    }

    public Integer getId() {
        return id;
    }

    public double getProvisionAdjustment() {
        return provisionAdjustment;
    }
}
