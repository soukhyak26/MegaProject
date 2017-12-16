package com.affaince.subscription.product.event;

import org.joda.time.LocalDateTime;

/**
 * Created by anayonkar on 29/4/16.
 */
public class ProvisionCreatedEvent {
    private Integer businessAccountId;
    private double provisionForPurchaseCost;
    private double provisionForLosses;
    private double provisionForBenefits;
    private double provisionForTaxes;
    private double provisionForOthers;
    private double provisionForCommonExpenses;
    private double provisionForSubscriptionSpecificExpenses;
    private double defaultPercentFixedExpensePerUnitPrice;
    private double defaultPercentVariableExpensePerUnitPrice;

    private LocalDateTime provisionDate;

    public ProvisionCreatedEvent() {
    }

    public ProvisionCreatedEvent(Integer businessAccountId,
                                 double provisionForPurchaseCost,
                                 double provisionForLosses,
                                 double provisionForBenefits,
                                 double provisionForTaxes,
                                 double provisionForOthers,
                                 double provisionForCommonExpenses,
                                 double provisionForSubscriptionSpecificExpenses,
                                 double defaultPercentFixedExpensePerUnitPrice,
                                 double defaultPercentVariableExpensePerUnitPrice,
                                 LocalDateTime provisionDate) {
        this.businessAccountId = businessAccountId;
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
    }

    public double getProvisionForLosses() {
        return provisionForLosses;
    }

    public void setProvisionForLosses(double provisionForLosses) {
        this.provisionForLosses = provisionForLosses;
    }

    public Integer getBusinessAccountId() {
        return businessAccountId;
    }

    public void setBusinessAccountId(Integer businessAccountId) {
        this.businessAccountId = businessAccountId;
    }

    public double getProvisionForPurchaseCost() {
        return provisionForPurchaseCost;
    }

    public void setProvisionForPurchaseCost(double provisionForPurchaseCost) {
        this.provisionForPurchaseCost = provisionForPurchaseCost;
    }

    public LocalDateTime getProvisionDate() {
        return provisionDate;
    }

    public void setProvisionDate(LocalDateTime provisionDate) {
        this.provisionDate = provisionDate;
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

    public double getDefaultPercentFixedExpensePerUnitPrice() {
        return defaultPercentFixedExpensePerUnitPrice;
    }

    public double getDefaultPercentVariableExpensePerUnitPrice() {
        return defaultPercentVariableExpensePerUnitPrice;
    }
}
