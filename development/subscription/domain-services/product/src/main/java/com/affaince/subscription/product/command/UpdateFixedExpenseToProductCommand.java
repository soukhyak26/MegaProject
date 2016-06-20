package com.affaince.subscription.product.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by mandar on 27-05-2016.
 */
public class UpdateFixedExpenseToProductCommand {
    @TargetAggregateIdentifier
    private String productId;
    private double operationExpense;

    public UpdateFixedExpenseToProductCommand(){}
    public UpdateFixedExpenseToProductCommand(String productId, double operationExpense) {
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
