package com.affaince.subscription.product.command.event;

import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import com.affaince.subscription.product.vo.VariableExpensePerProduct;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.Set;

/**
 * Created by mandar on 16-10-2016.
 */
public class VariableExpenseChangedEvent {
    private String productId;
    private LocalDate fromDate;
    private VariableExpensePerProduct variableExpensePerProduct;
    private Set<PriceTaggedWithProduct> updatedTaggedPriceVersions;

/*
    public VariableExpenseChangedEvent(String productId, LocalDate now, VariableExpensePerProduct newVariableExpenseVersion, double revisedBreakEvenPrice) {
    }
*/

    public VariableExpenseChangedEvent(String productId, LocalDate fromDate, VariableExpensePerProduct variableExpensePerProduct,Set<PriceTaggedWithProduct> updatedTaggedPriceVersions) {
        this.productId = productId;
        this.fromDate = fromDate;
        this.variableExpensePerProduct=variableExpensePerProduct;
        //this.revisedBreakEvenPrice=revisedBreakEvenPrice;
        this.updatedTaggedPriceVersions= updatedTaggedPriceVersions;
    }

    public String getProductId() {
        return productId;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public VariableExpensePerProduct getVariableExpensePerProduct() {
        return variableExpensePerProduct;
    }

    public Set<PriceTaggedWithProduct> getUpdatedTaggedPriceVersions() {
        return updatedTaggedPriceVersions;
    }
}
