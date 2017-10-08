package com.affaince.subscription.business.command.event;

public class PurchaseCostDebitedEvent {
    private Integer id;
    private String productId;
    private long getTotalProductSubscriptionCount;
    private double currentProductPurchasePrice;
    private double excessBudgetedAmount;

    public PurchaseCostDebitedEvent(Integer id, String productId, long getTotalProductSubscriptionCount, double currentProductPurchasePrice, double excessBudgetedAmount) {
        this.id = id;
        this.productId = productId;
        this.getTotalProductSubscriptionCount = getTotalProductSubscriptionCount;
        this.currentProductPurchasePrice = currentProductPurchasePrice;
        this.excessBudgetedAmount = excessBudgetedAmount;
    }

    public Integer getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public long getGetTotalProductSubscriptionCount() {
        return getTotalProductSubscriptionCount;
    }

    public double getCurrentProductPurchasePrice() {
        return currentProductPurchasePrice;
    }

    public double getExcessBudgetedAmount() {
        return excessBudgetedAmount;
    }

}
