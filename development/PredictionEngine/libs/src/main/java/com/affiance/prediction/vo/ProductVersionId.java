package com.affiance.prediction.vo;

import org.joda.time.LocalDate;

import java.io.Serializable;

/**
 * Created by mandark on 28-01-2016.
 */
public class ProductVersionId implements Serializable, Comparable<ProductVersionId> {
    private String productId;
    private LocalDate fromDate;
    public ProductVersionId(String productId, LocalDate fromDate) {
        this.productId = productId;
        this.fromDate = fromDate;
    }

    public ProductVersionId() {
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public LocalDate getFromDate() {
        return this.fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    @Override
    public String toString() {
        return "ProductVersionId{" +
                "productId='" + productId + '\'' +
                ", fromDate=" + fromDate +
                '}';
    }

    @Override
    public int compareTo(ProductVersionId o) {
        return this.getFromDate().compareTo(o.getFromDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductVersionId that = (ProductVersionId) o;

        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        return fromDate != null ? fromDate.equals(that.fromDate) : that.fromDate == null;

    }

    @Override
    public int hashCode() {
        int result = productId != null ? productId.hashCode() : 0;
        result = 31 * result + (fromDate != null ? fromDate.hashCode() : 0);
        return result;
    }
}
