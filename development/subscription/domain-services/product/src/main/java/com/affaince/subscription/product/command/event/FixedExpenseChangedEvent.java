package com.affaince.subscription.product.command.event;

import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import com.affaince.subscription.product.vo.FixedExpensePerProduct;
import org.joda.time.LocalDate;

import java.util.Set;

/**
 * Created by mandar on 16-10-2016.
 */
public class FixedExpenseChangedEvent {
    private String productId;
    private LocalDate fromDate;
    private FixedExpensePerProduct fixedExpensePerProduct;
    //private double revisedBreakEvenPrice;
    Set<PriceTaggedWithProduct> updatedTaggedPriceVersions;
    public FixedExpenseChangedEvent() {
    }

    public FixedExpenseChangedEvent(String productId, LocalDate fromDate, FixedExpensePerProduct fixedExpensePerProduct,Set<PriceTaggedWithProduct> updatedTaggedPriceVersions) {
        this.productId = productId;
        this.fromDate = fromDate;
        this.fixedExpensePerProduct=fixedExpensePerProduct;
        //this.revisedBreakEvenPrice=revisedBreakEvenPrice;
        this.updatedTaggedPriceVersions=updatedTaggedPriceVersions;
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

    public Set<PriceTaggedWithProduct> getUpdatedTaggedPriceVersions() {
        return updatedTaggedPriceVersions;
    }
}
