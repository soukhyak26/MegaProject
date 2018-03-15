package com.verifier.domains.product.vo;

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
        if (o == null || getClass() != o.getClass()) return false;

        ProductMonthlyVersionId that = (ProductMonthlyVersionId) o;

        if (!productId.equals(that.productId)) return false;
        return monthOfYear.equals(that.monthOfYear);

    }

    @Override
    public int hashCode() {
        int result = productId.hashCode();
        result = 31 * result + monthOfYear.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ProductMonthlyVersionId{" +
                "productId='" + productId + '\'' +
                ", monthOfYear=" + monthOfYear +
                '}';
    }
}
