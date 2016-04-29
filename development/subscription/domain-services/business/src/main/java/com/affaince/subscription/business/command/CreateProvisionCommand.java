package com.affaince.subscription.business.command;

import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 29/4/16.
 */
public class CreateProvisionCommand {
    private double provisionForPurchaseCost;
    private LocalDate provisionDate;
    private String businessAccountId;

    public CreateProvisionCommand(double provisionForPurchaseCost, LocalDate provisionDate) {
        this.provisionForPurchaseCost = provisionForPurchaseCost;
        this.provisionDate = provisionDate;
        businessAccountId = Integer.valueOf(provisionDate.getYear()).toString();
    }

    public double getProvisionForPurchaseCost() {
        return provisionForPurchaseCost;
    }

    public LocalDate getProvisionDate() {
        return provisionDate;
    }

    public void setProvisionForPurchaseCost(double provisionForPurchaseCost) {
        this.provisionForPurchaseCost = provisionForPurchaseCost;
    }

    public void setProvisionDate(LocalDate provisionDate) {
        this.provisionDate = provisionDate;
    }

    public String getBusinessAccountId() {
        return businessAccountId;
    }
}
