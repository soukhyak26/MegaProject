package com.verifier.domains.product.vo;

import org.joda.time.LocalDate;

import java.io.Serializable;

/**
 * Created by mandar on 4/26/2017.
 */
public class ProductwiseFixedExpenseId implements Serializable {
    private final String productId;
    private final LocalDate startDate;

    public ProductwiseFixedExpenseId(String productId, LocalDate startDate) {
        this.productId = productId;
        this.startDate = startDate;
    }

    public String getProductId() {
        return productId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

}
