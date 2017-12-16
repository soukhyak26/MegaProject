package com.affaince.subscription.business.event;

/**
 * Created by mandar on 18-02-2017.
 */
public class PurchaseCostRevisedEvent {
    private Integer businessAccountId;
    private String productId;
    private double additionalBudgetProvision;

    public PurchaseCostRevisedEvent(Integer businessAccountId, String productId, double additionalBudgetProvision) {
        this.businessAccountId = businessAccountId;
        this.productId = productId;
        this.additionalBudgetProvision = additionalBudgetProvision;
    }

    public Integer getBusinessAccountId() {
        return businessAccountId;
    }

    public String getProductId() {
        return productId;
    }

    public double getAdditionalBudgetProvision() {
        return additionalBudgetProvision;
    }
}
