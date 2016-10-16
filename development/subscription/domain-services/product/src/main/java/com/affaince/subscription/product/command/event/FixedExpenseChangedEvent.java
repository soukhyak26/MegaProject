package com.affaince.subscription.product.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 16-10-2016.
 */
public class FixedExpenseChangedEvent {
    private String productId;
    private LocalDate fromDate;
    private double operationExpense;

    public FixedExpenseChangedEvent() {
    }

    public FixedExpenseChangedEvent(String productId, LocalDate fromDate, double operationExpense) {
        this.productId = productId;
        this.fromDate = fromDate;
        this.operationExpense = operationExpense;
    }

    public String getProductId() {
        return productId;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public double getOperationExpense() {
        return operationExpense;
    }
}
