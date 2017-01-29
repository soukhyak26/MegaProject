package com.affaince.subscription.business.command;

/**
 * Created by mandar on 29-01-2017.
 */
public class AddProductContributionToPurchaseCostRevenueAndProfitCommand {
    private final String productId;
    private Integer businessAccountId;
    private final double purchaseCostContribution;
    private final double revenueContribution;
    private final double profitContribution;

    public AddProductContributionToPurchaseCostRevenueAndProfitCommand(String productId, Integer businessAccountId, double purchaseCostContribution, double revenueContribution, double profitContribution) {
        this.productId = productId;
        this.businessAccountId=businessAccountId;
        this.purchaseCostContribution = purchaseCostContribution;
        this.revenueContribution = revenueContribution;
        this.profitContribution = profitContribution;
    }

    public String getProductId() {
        return productId;
    }

    public Integer getBusinessAccountId() {
        return businessAccountId;
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
