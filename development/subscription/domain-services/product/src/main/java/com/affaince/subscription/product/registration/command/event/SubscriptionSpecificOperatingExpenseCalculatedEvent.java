package com.affaince.subscription.product.registration.command.event;

/**
 * Created by rsavaliya on 13/2/16.
 */
public class SubscriptionSpecificOperatingExpenseCalculatedEvent {
    private String productId;
    private double operationExpense;

    public SubscriptionSpecificOperatingExpenseCalculatedEvent(String productId, double operationExpense) {
        this.productId = productId;
        this.operationExpense = operationExpense;
    }

    public String getProductId() {
        return productId;
    }

    public double getOperationExpense() {
        return operationExpense;
    }
}