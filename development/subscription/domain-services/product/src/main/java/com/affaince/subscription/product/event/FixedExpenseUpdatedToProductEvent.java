package com.affaince.subscription.product.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 27-05-2016.
 */
public class FixedExpenseUpdatedToProductEvent {
    private String productId;
    private LocalDate fromDate;
    private double operationExpense;

    public FixedExpenseUpdatedToProductEvent() {
    }

    public FixedExpenseUpdatedToProductEvent(String productId, LocalDate now, double operationExpense) {
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
