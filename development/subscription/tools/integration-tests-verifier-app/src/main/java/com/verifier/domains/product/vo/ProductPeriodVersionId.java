package com.verifier.domains.product.vo;

import org.joda.time.Interval;

import java.io.Serializable;

/**
 * Created by mandark on 21-02-2016.
 */
public class ProductPeriodVersionId implements Serializable {
    private String productId;
    //private YearMonth monthOfYear;
    private Interval period;

    public ProductPeriodVersionId(String productId, Interval period) {
        this.productId = productId;
        this.period = period;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Interval getPeriod() {
        return period;
    }

    public void setPeriod(Interval period) {
        this.period = period;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductPeriodVersionId that = (ProductPeriodVersionId) o;

        if (!productId.equals(that.productId)) return false;
        return period.equals(that.period);

    }

    @Override
    public int hashCode() {
        int result = productId.hashCode();
        result = 31 * result + period.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ProductPeriodVersionId{" +
                "productId='" + productId + '\'' +
                ", period=" + period +
                '}';
    }
}
