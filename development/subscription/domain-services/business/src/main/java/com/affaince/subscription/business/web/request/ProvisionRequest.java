package com.affaince.subscription.business.web.request;

import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 29/4/16.
 */
public class ProvisionRequest {
    private double provisionForPurchaseCost;
    private LocalDate provisionDate;

    public ProvisionRequest() {

    }

    public ProvisionRequest(double provisionForPurchaseCost, LocalDate provisionDate) {
        this.provisionForPurchaseCost = provisionForPurchaseCost;
        this.provisionDate = provisionDate;
    }

    public double getProvisionForPurchaseCost() {
        return provisionForPurchaseCost;
    }

    public void setProvisionForPurchaseCost(double provisionForPurchaseCost) {
        this.provisionForPurchaseCost = provisionForPurchaseCost;
    }

    public LocalDate getProvisionDate() {
        return provisionDate;
    }

    public void setProvisionDate(LocalDate provisionDate) {
        this.provisionDate = provisionDate;
    }

    @Override
    public String toString() {
        return "ProvisionRequest{" +
                "provisionForPurchaseCost=" + provisionForPurchaseCost +
                ", provisionDate=" + provisionDate +
                '}';
    }
}
