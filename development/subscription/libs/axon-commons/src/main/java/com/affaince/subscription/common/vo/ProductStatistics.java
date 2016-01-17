package com.affaince.subscription.common.vo;

/**
 * Created by rsavaliya on 17/1/16.
 */
public class ProductStatistics {

    private String productId;
    private long productSubscriptionCount;
    private double subscribedProductRevenue;
    private double subscribedProductNetProfit;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public long getProductSubscriptionCount() {
        return productSubscriptionCount;
    }

    public void setProductSubscriptionCount(long productSubscriptionCount) {
        this.productSubscriptionCount = productSubscriptionCount;
    }

    public double getSubscribedProductRevenue() {
        return subscribedProductRevenue;
    }

    public void setSubscribedProductRevenue(double subscribedProductRevenue) {
        this.subscribedProductRevenue = subscribedProductRevenue;
    }

    public double getSubscribedProductNetProfit() {
        return subscribedProductNetProfit;
    }

    public void setSubscribedProductNetProfit(double subscribedProductNetProfit) {
        this.subscribedProductNetProfit = subscribedProductNetProfit;
    }
}
