package com.affaince.subscription.business.event;

/**
 * Created by mandar on 29-01-2017.
 */
public class ExpectedProductContributionToPurchaseExpenseRevenueAndProfitAddedEvent {
    private String productId;
    private double purchaseCostContribution;
    private double revenueContribution;
    private double profitContribution;
    public ExpectedProductContributionToPurchaseExpenseRevenueAndProfitAddedEvent(String productId, double purchaseCostContribution, double revenueContribution, double profitContribution) {
        this.productId=productId;
        this.purchaseCostContribution = purchaseCostContribution;
        this.revenueContribution = revenueContribution;
        this.profitContribution = profitContribution;
    }

    public String getProductId() {
        return productId;
    }

    public double getPurchaseCostContribution() {
        return purchaseCostContribution;
    }

    public double getRevenueContribution() {
        return revenueContribution;
    }

    public double getProfitContribution() {
        return profitContribution;
    }
}