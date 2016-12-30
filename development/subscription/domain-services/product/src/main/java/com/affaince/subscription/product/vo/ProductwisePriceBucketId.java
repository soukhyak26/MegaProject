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
}
