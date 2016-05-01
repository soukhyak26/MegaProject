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
    private double provisionForTaxes;
    private double provisionForOthers;
    private double provisionForCommonExpenses;
    private double provisionForNodalAccount;
    private double provisionForRevenue;
    private double provisionForBookinAmount;
    private double provisionForSubscriptionSpecificExpenses;
    private LocalDate provisionDate;

    public CreateProvisionEvent() {

    }
    public CreateProvisionEvent(String businessAccountId,
                                double provisionForPurchaseCost,
                                double provisionForLosses,
                                double provisionForBenefits,
                                double provisionForTaxes,
                                double provisionForOthers,
                                double provisionForCommonExpenses,
                                double provisionForNodalAccount,
                                double provisionForRevenue,
                                double provisionForBookinAmount,
                                double provisionForSubscriptionSpecificExpenses,
                                LocalDate provisionDate) {
        this.businessAccountId = businessAccountId;
        this.provisionForPurchaseCost = provisionForPurchaseCost;
        this.provisionForLosses = provisionForLosses;
        this.provisionForBenefits = provisionForBenefits;
        this.provisionForTaxes = provisionForTaxes;
        this.provisionForOthers = provisionForOthers;
        this.provisionForCommonExpenses = provisionForCommonExpenses;
        this.provisionForNodalAccount = provisionForNodalAccount;
        this.provisionForRevenue = provisionForRevenue;
        this.provisionForBookinAmount = provisionForBookinAmount;
        this.provisionForSubscriptionSpecificExpenses = provisionForSubscriptionSpecificExpenses;
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

    public double getProvisionForNodalAccount() {
        return provisionForNodalAccount;
    }

    public void setProvisionForNodalAccount(double provisionForNodalAccount) {
        this.provisionForNodalAccount = provisionForNodalAccount;
    }

    public double getProvisionForRevenue() {
        return provisionForRevenue;
    }

    public void setProvisionForRevenue(double provisionForRevenue) {
        this.provisionForRevenue = provisionForRevenue;
    }

    public double getProvisionForBookinAmount() {
        return provisionForBookinAmount;
    }

    public void setProvisionForBookinAmount(double provisionForBookinAmount) {
        this.provisionForBookinAmount = provisionForBookinAmount;
    }

    public double getProvisionForSubscriptionSpecificExpenses() {
        return provisionForSubscriptionSpecificExpenses;
    }

    public void setProvisionForSubscriptionSpecificExpenses(double provisionForSubscriptionSpecificExpenses) {
        this.provisionForSubscriptionSpecificExpenses = provisionForSubscriptionSpecificExpenses;
    }
}
