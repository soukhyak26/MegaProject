package com.affaince.subscription.business.web.request;

import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 29/4/16.
 */
public class ProvisionRequest {
    private double provisionForPurchaseCost;
    private double provisionForLosses;
    private double provisionForBenefits;
    /*private double provisionForTaxes;*/
    private LocalDate provisionDate;

    public ProvisionRequest() {

    }

    public ProvisionRequest(double provisionForPurchaseCost, double provisionForLosses, double provisionForBenefits, LocalDate provisionDate) {
        this.provisionForPurchaseCost = provisionForPurchaseCost;
        this.provisionForLosses = provisionForLosses;
        this.provisionForBenefits = provisionForBenefits;
        this.provisionDate = provisionDate;
    }

    public double getProvisionForPurchaseCost() {
        return provisionForPurchaseCost;
    }

    public void setProvisionForPurchaseCost(double provisionForPurchaseCost) {
        this.provisionForPurchaseCost = provisionForPurchaseCost;
    }

    public double getProvisionForLosses() {
        return provisionForLosses;
    }

    public void setProvisionForLosses(double provisionForLosses) {
        this.provisionForLosses = provisionForLosses;
    }

    public double getProvisionForBenefits() {
        return provisionForBenefits;
    }

    public void setProvisionForBenefits(double provisionForBenefits) {
        this.provisionForBenefits = provisionForBenefits;
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
                ", provisionForLosses=" + provisionForLosses +
                ", provisionForBenefits=" + provisionForBenefits +
                ", provisionDate=" + provisionDate +
                '}';
    }
}
