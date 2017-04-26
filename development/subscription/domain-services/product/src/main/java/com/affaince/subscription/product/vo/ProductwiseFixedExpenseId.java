package com.affaince.subscription.product.vo;

import org.joda.time.LocalDate;

import java.io.Serializable;

/**
 * Created by mandar on 4/26/2017.
 */
public class ProductwiseFixedExpenseId implements Serializable {
    private final String productId;
    private final LocalDate fromDate;

    public ProductwiseFixedExpenseId(String productId, LocalDate fromDate) {
        this.productId = productId;
        this.fromDate = fromDate;
    }

    public String getProductId() {
        return productId;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

}
