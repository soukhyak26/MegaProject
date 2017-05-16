package com.affaince.subscription.business.command.event;

/**
 * Created by mandar on 30-01-2017.
 */
public class ExcessProfitDonatedToNodalAccountEvent {
    private String productId;
    private double excessProfit;
    public ExcessProfitDonatedToNodalAccountEvent(){}
    public ExcessProfitDonatedToNodalAccountEvent(String productId, double excessProfit) {
        this.productId=productId;
        this.excessProfit=excessProfit;
    }

    public String getProductId() {
        return productId;
    }

    public double getExcessProfit() {
        return excessProfit;
    }

}
