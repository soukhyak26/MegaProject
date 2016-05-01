package com.affaince.subscription.business.web.request;

import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 29/4/16.
 */
public class ProvisionRequest {
    private double provisionForPurchaseCost;
    private double provisionForLosses;
    private double provisionForBenefits;
    private double provisionForTaxes;
    private double provisionForOthers;
    private double provisionForCommonExpenses;
    private double provisionForNodalAccount;
    private double provisionForRevenue;
    private double provisionForBookingAmount;
    private double provisionForSubscriptionSpecificExpenses;
    private LocalDate provisionDate;

    public ProvisionRequest() {

    }

    public ProvisionRequest(double provisionForPurchaseCost,
                            double provisionForLosses,
                            double provisionForBenefits,
                            double provisionForTaxes,
                            double provisionForOthers,
                            double provisionForCommonExpenses,
                            double provisionForNodalAccount,
                            double provisionForRevenue,
                            double provisionForBookingAmount,
                            double provisionForSubscriptionSpecificExpenses,
                            LocalDate provisionDate) {
        this.provisionForPurchaseCost = provisionForPurchaseCost;
        this.provisionForLosses = provisionForLosses;
        this.provisionForBenefits = provisionForBenefits;
        this.provisionForTaxes = provisionForTaxes;
        this.provisionForOthers = provisionForOthers;
        this.provisionForCommonExpenses = provisionForCommonExpenses;
        this.provisionForNodalAccount = provisionForNodalAccount;
        this.provisionForRevenue = provisionForRevenue;
        this.provisionForBookingAmount = provisionForBookingAmount;
        this.provisionForSubscriptionSpecificExpenses = provisionForSubscriptionSpecificExpenses;
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

    public double getProvisionForBookingAmount() {
        return provisionForBookingAmount;
    }

    public void setProvisionForBookingAmount(double provisionForBookingAmount) {
        this.provisionForBookingAmount = provisionForBookingAmount;
    }

    public double getProvisionForSubscriptionSpecificExpenses() {
        return provisionForSubscriptionSpecificExpenses;
    }

    public void setProvisionForSubscriptionSpecificExpenses(double provisionForSubscriptionSpecificExpenses) {
        this.provisionForSubscriptionSpecificExpenses = provisionForSubscriptionSpecificExpenses;
    }

    public LocalDate getProvisionDate() {
        return provisionDate;
    }

    public void setProvisionDate(LocalDate provisionDate) {
        this.provisionDate = provisionDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProvisionRequest{");
        sb.append("provisionForPurchaseCost=").append(provisionForPurchaseCost);
        sb.append(", provisionForLosses=").append(provisionForLosses);
        sb.append(", provisionForBenefits=").append(provisionForBenefits);
        sb.append(", provisionForTaxes=").append(provisionForTaxes);
        sb.append(", provisionForOthers=").append(provisionForOthers);
        sb.append(", provisionForCommonExpenses=").append(provisionForCommonExpenses);
        sb.append(", provisionForNodalAccount=").append(provisionForNodalAccount);
        sb.append(", provisionForRevenue=").append(provisionForRevenue);
        sb.append(", provisionForBookingAmount=").append(provisionForBookingAmount);
        sb.append(", provisionForSubscriptionSpecificExpenses=").append(provisionForSubscriptionSpecificExpenses);
        sb.append(", provisionDate=").append(provisionDate);
        sb.append('}');
        return sb.toString();
    }
}
