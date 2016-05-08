package com.affaince.subscription.product.registration.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rsavaliya on 8/5/16.
 */
public class UpdateDeliveryExpenseToProductCommand {
    @TargetAggregateIdentifier
    private String productId;
    private double operationExpense;

    public UpdateDeliveryExpenseToProductCommand(String productId, double operationExpense) {
        this.productId = productId;
        this.operationExpense = operationExpense;
    }

    public UpdateDeliveryExpenseToProductCommand() {
    }

    public String getProductId() {
        return productId;
    }

    public double getOperationExpense() {
        return operationExpense;
    }
}
