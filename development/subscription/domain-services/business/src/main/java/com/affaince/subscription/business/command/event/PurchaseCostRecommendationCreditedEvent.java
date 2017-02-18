package com.affaince.subscription.business.command.event;

/**
 * Created by mandar on 15-02-2017.
 */
public class PurchaseCostRecommendationCreditedEvent {
    private Integer id;
    private String productId;
    private long getTotalProductSubscriptionCount;
    private double currentProductPurchasePrice;
    private double additionalBudgetedAmount;

    public PurchaseCostRecommendationCreditedEvent(Integer id, String productId, long totalSubscriptionsRegistered, double productPurchasePricePerUnit, double additionalBudgetedAmount) {
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
