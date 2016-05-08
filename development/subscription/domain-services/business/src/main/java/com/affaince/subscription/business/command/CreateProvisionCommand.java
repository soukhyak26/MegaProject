package com.affaince.subscription.business.command;

import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 29/4/16.
 */
public class CreateProvisionCommand {
    private double provisionForPurchaseCost;
    private double provisionForLosses;
    private double provisionForBenefits;
    private double provisionForTaxes;
    private double provisionForOthers;
    private double provisionForCommonExpenses;
    private double provisionForSubscriptionSpecificExpenses;
    private LocalDate provisionDate;
    private String businessAccountId;

    public CreateProvisionCommand(double provisionForPurchaseCost,
                                  double provisionForLosses,
                                  double provisionForBenefits,
                                  double provisionForTaxes,
                                  double provisionForOthers,
                                  double provisionForCommonExpenses,
                                  double provisionForSubscriptionSpecificExpenses,
                                  LocalDate provisionDate) {
        this.provisionForPurchaseCost = provisionForPurchaseCost;
        this.provisionForLosses = provisionForLosses;
        this.provisionForBenefits = provisionForBenefits;
        this.provisionForTaxes = provisionForTaxes;
        this.provisionForOthers = provisionForOthers;
        this.provisionForCommonExpenses = provisionForCommonExpenses;
        this.provisionForSubscriptionSpecificExpenses = provisionForSubscriptionSpecificExpenses;
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

    public double getProvisionForTaxes() {
        return provisionForTaxes;
    }

    public void setProvisionForTaxes(double provisionForTaxes) {
        this.provisionForTaxes = provisionForTaxes;
    }

    public double getProvisionForOthers() {
        return provisionForOthers;
    }

    public void setProvisionForOthers(double provisionForOthers) {
        this.provisionForOthers = provisionForOthers;
    }

    public double getProvisionForCommonExpenses() {
        return provisionForCommonExpenses;
    }

    public void setProvisionForCommonExpenses(double provisionForCommonExpenses) {
        this.provisionForCommonExpenses = provisionForCommonExpenses;
    }

    public double getProvisionForSubscriptionSpecificExpenses() {
        return provisionForSubscriptionSpecificExpenses;
    }

    public void setProvisionForSubscriptionSpecificExpenses(double provisionForSubscriptionSpecificExpenses) {
        this.provisionForSubscriptionSpecificExpenses = provisionForSubscriptionSpecificExpenses;
    }
}
