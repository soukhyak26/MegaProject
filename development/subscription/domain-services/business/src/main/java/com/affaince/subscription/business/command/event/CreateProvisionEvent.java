package com.affaince.subscription.business.command.event;

import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 29/4/16.
 */
public class CreateProvisionEvent {
    private String businessAccountId;
    private double provisionForPurchaseCost;
    private double provisionForLosses;
    private double provisionForBenefits;
    private LocalDate provisionDate;

    public CreateProvisionEvent() {

    }
    public CreateProvisionEvent(String businessAccountId,
                                double provisionForPurchaseCost,
                                double provisionForLosses,
                                double provisionForBenefits,
                                LocalDate provisionDate) {
        this.businessAccountId = businessAccountId;
        this.provisionForPurchaseCost = provisionForPurchaseCost;
        this.provisionForLosses = provisionForLosses;
        this.provisionForBenefits = provisionForBenefits;
        this.provisionDate = provisionDate;
    }

    public double getProvisionForLosses() {
        return provisionForLosses;
    }

    public void setProvisionForLosses(double provisionForLosses) {
        this.provisionForLosses = provisionForLosses;
    }

    public String getBusinessAccountId() {
        return businessAccountId;
    }

    public void setBusinessAccountId(String businessAccountId) {
        this.businessAccountId = businessAccountId;
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

    public double getProvisionForBenefits() {
        return provisionForBenefits;
    }

    public void setProvisionForBenefits(double provisionForBenefits) {
        this.provisionForBenefits = provisionForBenefits;
    }
}
