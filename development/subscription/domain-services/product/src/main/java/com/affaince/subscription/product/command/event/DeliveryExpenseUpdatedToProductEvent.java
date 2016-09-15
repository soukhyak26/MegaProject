package com.affaince.subscription.product.command.event;

import org.joda.time.LocalDateTime;

/**
 * Created by rsavaliya on 8/5/16.
 */
public class DeliveryExpenseUpdatedToProductEvent {
    private String productId;
    private LocalDateTime fromDate;
    private double operationExpense;

    public DeliveryExpenseUpdatedToProductEvent(String productId, LocalDateTime fromDate, double operationExpense) {
        this.productId = productId;
        this.fromDate = fromDate;
        this.operationExpense = operationExpense;
    }

    public DeliveryExpenseUpdatedToProductEvent() {
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
