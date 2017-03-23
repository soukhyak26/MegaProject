package com.affaince.subscription.product.vo;

import java.io.Serializable;

/**
 * Created by mandar on 29-12-2016.
 */
public class ProductwisePriceBucketId implements Serializable{
    private final String productId;
    private final String priceBucketId;

    public ProductwisePriceBucketId(String productId, String priceBucketId) {
        this.productId = productId;
        this.priceBucketId = priceBucketId;
    }

    public String getProductId() {
        return productId;
    }

    public String getPriceBucketId() {
        return priceBucketId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductwisePriceBucketId)) return false;

        ProductwisePriceBucketId that = (ProductwisePriceBucketId) o;

        if (!getProductId().equals(that.getProductId())) return false;
        return getPriceBucketId().equals(that.getPriceBucketId());
    }

    @Override
    public int hashCode() {
        int result = getProductId().hashCode();
        result = 31 * result + getPriceBucketId().hashCode();
        return result;
    }
}
