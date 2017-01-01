package com.affaince.subscription.business.command;

import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 29/4/16.
 */
public class CreateProvisionCommand {
    private Double provisionForPurchaseCost;
    private Double provisionForLosses;
    private Double provisionForBenefits;
    private Double provisionForTaxes;
    private Double provisionForOthers;
    private Double provisionForCommonExpenses;
    private Double provisionForSubscriptionSpecificExpenses;
    private double defaultPercentFixedExpensePerUnitPrice;
    private double defaultPercentVariableExpensePerUnitPrice;
    private LocalDate provisionDate;
    private String businessAccountId;

    public CreateProvisionCommand(Double provisionForPurchaseCost,
                                  Double provisionForLosses,
                                  Double provisionForBenefits,
                                  Double provisionForTaxes,
                                  Double provisionForOthers,
                                  Double provisionForCommonExpenses,
                                  Double provisionForSubscriptionSpecificExpenses,
                                  double defaultPercentFixedExpensePerUnitPrice,
                                  double defaultPercentVariableExpensePerUnitPrice,
                                  LocalDate provisionDate) {
        this.provisionForPurchaseCost = provisionForPurchaseCost;
        this.provisionForLosses = provisionForLosses;
        this.provisionForBenefits = provisionForBenefits;
        this.provisionForTaxes = provisionForTaxes;
        this.provisionForOthers = provisionForOthers;
        this.provisionForCommonExpenses = provisionForCommonExpenses;
        this.provisionForSubscriptionSpecificExpenses = provisionForSubscriptionSpecificExpenses;
        this.defaultPercentFixedExpensePerUnitPrice=defaultPercentFixedExpensePerUnitPrice;
        this.defaultPercentVariableExpensePerUnitPrice=defaultPercentVariableExpensePerUnitPrice;
        this.provisionDate = provisionDate;
        businessAccountId = Integer.valueOf(provisionDate.getYear()).toString();
    }

    public Double getProvisionForLosses() {
        return provisionForLosses;
    }

    public void setProvisionForLosses(double provisionForLosses) {
        this.provisionForLosses = provisionForLosses;
    }

    public Double getProvisionForPurchaseCost() {
        return provisionForPurchaseCost;
    }

    public LocalDate getProvisionDate() {
        return provisionDate;
    }

    public void setProvisionForPurchaseCost(Double provisionForPurchaseCost) {
        this.provisionForPurchaseCost = provisionForPurchaseCost;
    }

    public void setProvisionDate(LocalDate provisionDate) {
        this.provisionDate = provisionDate;
    }

    public String getBusinessAccountId() {
        return businessAccountId;
    }

    public Double getProvisionForBenefits() {
        return provisionForBenefits;
    }

    public void setProvisionForBenefits(Double provisionForBenefits) {
        this.provisionForBenefits = provisionForBenefits;
    }

    public Double getProvisionForTaxes() {
        return provisionForTaxes;
    }

    public void setProvisionForTaxes(Double provisionForTaxes) {
        this.provisionForTaxes = provisionForTaxes;
    }

    public Double getProvisionForOthers() {
        return provisionForOthers;
    }

    public void setProvisionForOthers(Double provisionForOthers) {
        this.provisionForOthers = provisionForOthers;
    }

    public Double getProvisionForCommonExpenses() {
        return provisionForCommonExpenses;
    }

    public void setProvisionForCommonExpenses(Double provisionForCommonExpenses) {
        this.provisionForCommonExpenses = provisionForCommonExpenses;
    }

    public Double getProvisionForSubscriptionSpecificExpenses() {
        return provisionForSubscriptionSpecificExpenses;
    }

    public void setProvisionForSubscriptionSpecificExpenses(Double provisionForSubscriptionSpecificExpenses) {
        this.provisionForSubscriptionSpecificExpenses = provisionForSubscriptionSpecificExpenses;
    }

    public double getDefaultPercentFixedExpensePerUnitPrice() {
        return defaultPercentFixedExpensePerUnitPrice;
    }

    public double getDefaultPercentVariableExpensePerUnitPrice() {
        return defaultPercentVariableExpensePerUnitPrice;
    }
}
