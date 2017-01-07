package com.affaince.subscription.product.command.event;

import com.affaince.subscription.product.vo.VariableExpensePerProduct;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

/**
 * Created by mandar on 16-10-2016.
 */
public class VariableExpenseChangedEvent {
    private String productId;
    private LocalDateTime fromDate;
    private VariableExpensePerProduct variableExpensePerProduct;
    private double revisedBreakEvenPrice;

    public VariableExpenseChangedEvent(String productId, LocalDate now, VariableExpensePerProduct newVariableExpenseVersion, double revisedBreakEvenPrice) {
    }

    public VariableExpenseChangedEvent(String productId, LocalDateTime fromDate, VariableExpensePerProduct variableExpensePerProduct,double revisedBreakEvenPrice) {
        this.productId = productId;
        this.fromDate = fromDate;
        this.variableExpensePerProduct=variableExpensePerProduct;
        this.revisedBreakEvenPrice=revisedBreakEvenPrice;
    }

    public String getProductId() {
        return productId;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public VariableExpensePerProduct getVariableExpensePerProduct() {
        return variableExpensePerProduct;
    }

    public double getRevisedBreakEvenPrice() {
        return revisedBreakEvenPrice;
    }
}
