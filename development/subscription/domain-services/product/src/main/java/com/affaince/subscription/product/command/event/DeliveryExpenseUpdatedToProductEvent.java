package com.affaince.subscription.product.command.event;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

public class DeliveryExpenseUpdatedToProductEvent {
    private String productId;
    private LocalDate fromDate;
    private double operationExpense;

    public DeliveryExpenseUpdatedToProductEvent(String productId, LocalDate fromDate, double operationExpense) {
        this.productId = productId;
        this.fromDate = fromDate;
        this.operationExpense = operationExpense;
    }

    public DeliveryExpenseUpdatedToProductEvent() {
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
