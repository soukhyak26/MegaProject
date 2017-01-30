package com.affaince.subscription.business.command;

/**
 * Created by mandar on 30-01-2017.
 */
public class DonateExcessProfitForAProductToNodalAccountCommand {
    private Integer businessAccountId;
    private String productId;
    private double excessProfit;

    public DonateExcessProfitForAProductToNodalAccountCommand(Integer businessAccountId, String productId, double excessProfit) {
        this.businessAccountId = businessAccountId;
        this.productId = productId;
        this.excessProfit = excessProfit;
    }

    public Integer getBusinessAccountId() {
        return businessAccountId;
    }

    public String getProductId() {
        return productId;
    }

    public double getExcessProfit() {
        return excessProfit;
    }
}
