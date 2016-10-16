package com.affaince.subscription.product.command.event;

import org.joda.time.LocalDateTime;

/**
 * Created by mandar on 16-10-2016.
 */
public class VariableExpenseChangedEvent {
    private String productId;
    private LocalDateTime fromDate;
    private double operationExpense;

    public VariableExpenseChangedEvent() {
    }

    public VariableExpenseChangedEvent(String productId, LocalDateTime fromDate, double operationExpense) {
        this.productId = productId;
        this.fromDate = fromDate;
        this.operationExpense = operationExpense;
    }

    public String getProductId() {
        return productId;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public double getOperationExpense() {
        return operationExpense;
    }
}
