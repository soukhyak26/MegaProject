package com.affaince.subscription.pricing.query.view;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        ProductMonthlyVersionId that = (ProductMonthlyVersionId) o;

        if (!this.getProductId().equals(that.getProductId())) return false;
        return getMonthOfYear().equals(that.getMonthOfYear());

    }

    @Override
    public int hashCode() {
        int result = this.getProductId().hashCode();
        result = 31 * result + this.getMonthOfYear().hashCode();
        return result;
    }
}
