package com.affaince.subscription.product.registration.vo;

import org.joda.time.YearMonth;

import java.io.Serializable;

/**
 * Created by mandark on 21-02-2016.
 */
public class ProductMonthlyVersionId implements Serializable {
    private String productId;
    private YearMonth monthOfYear;

    public ProductMonthlyVersionId(String productId, YearMonth monthOfYear) {
        this.productId = productId;
        this.monthOfYear = monthOfYear;
    }

    public String getProductId() {
        return this.productId;
    }

    public YearMonth getMonthOfYear() {
        return this.monthOfYear;
    }
}
