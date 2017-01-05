package com.affaince.subscription.product.command.event;

import com.affaince.subscription.product.vo.FixedExpensePerProduct;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 16-10-2016.
 */
public class FixedExpenseChangedEvent {
    private String productId;
    private LocalDate fromDate;
    private FixedExpensePerProduct fixedExpensePerProduct;
    private double revisedBreakEvenPrice;
    public FixedExpenseChangedEvent() {
    }

    public FixedExpenseChangedEvent(String productId, LocalDate fromDate, FixedExpensePerProduct fixedExpensePerProduct,double revisedBreakEvenPrice) {
        this.productId = productId;
        this.fromDate = fromDate;
        this.fixedExpensePerProduct=fixedExpensePerProduct;
        this.revisedBreakEvenPrice=revisedBreakEvenPrice;
    }

    public String getProductId() {
        return productId;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public FixedExpensePerProduct getFixedExpensePerProduct() {
        return fixedExpensePerProduct;
    }

    public double getRevisedBreakEvenPrice() {
        return revisedBreakEvenPrice;
    }
}
