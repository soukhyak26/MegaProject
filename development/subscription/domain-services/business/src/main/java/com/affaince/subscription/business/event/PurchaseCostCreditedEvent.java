package com.affaince.subscription.business.event;

public class PurchaseCostCreditedEvent {
    private Integer id;
    private String productId;
    private long getTotalProductSubscriptionCount;
    private double currentProductPurchasePrice;
    private double additionalBudgetedAmount;

    public PurchaseCostCreditedEvent(Integer id, String productId, long getTotalProductSubscriptionCount, double currentProductPurchasePrice, double additionalBudgetedAmount) {
        this.id = id;
        this.productId = productId;
        this.getTotalProductSubscriptionCount = getTotalProductSubscriptionCount;
        this.currentProductPurchasePrice = currentProductPurchasePrice;
        this.additionalBudgetedAmount = additionalBudgetedAmount;
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

    public double getAdditionalBudgetedAmount() {
        return additionalBudgetedAmount;
    }
}
