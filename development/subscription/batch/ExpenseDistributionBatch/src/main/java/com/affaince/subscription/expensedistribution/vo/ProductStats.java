package com.affaince.subscription.expensedistribution.vo;

import java.util.Map;

/**
 * Created by rsavaliya on 14/2/16.
 */
public class ProductStats {
    private String productId;
    private Map<Integer, Integer> subscriptionsPerMonth;
    private double purchasePrice;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Map<Integer, Integer> getSubscriptionsPerMonth() {
        return subscriptionsPerMonth;
    }

    public void setSubscriptionsPerMonth(Map<Integer, Integer> subscriptionsPerMonth) {
        this.subscriptionsPerMonth = subscriptionsPerMonth;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}
