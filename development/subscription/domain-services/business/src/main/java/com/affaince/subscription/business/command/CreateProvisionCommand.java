package com.affaince.subscription.business.command;

import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 29/4/16.
 */
public class CreateProvisionCommand {
    private double provisionForPurchaseCost;
    private double provisionForLosses;
    private double provisionForBenefits;
    private LocalDate provisionDate;
    private String businessAccountId;

    public CreateProvisionCommand(double provisionForPurchaseCost,
                                  double provisionForLosses,
                                  double provisionForBenefits,
                                  LocalDate provisionDate) {
        this.provisionForPurchaseCost = provisionForPurchaseCost;
        this.provisionForLosses = provisionForLosses;
        this.provisionForBenefits = provisionForBenefits;
        this.provisionDate = provisionDate;
        businessAccountId = Integer.valueOf(provisionDate.getYear()).toString();
    }

    public double getProvisionForLosses() {
        return provisionForLosses;
    }

    public void setProvisionForLosses(double provisionForLosses) {
        this.provisionForLosses = provisionForLosses;
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

    public double getProvisionForBenefits() {
        return provisionForBenefits;
    }

    public void setProvisionForBenefits(double provisionForBenefits) {
        this.provisionForBenefits = provisionForBenefits;
    }
}
