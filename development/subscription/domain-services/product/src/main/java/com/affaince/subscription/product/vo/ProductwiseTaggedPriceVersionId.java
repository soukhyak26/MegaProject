package com.affaince.subscription.product.vo;

import java.io.Serializable;

/**
 * Created by mandar on 31-12-2016.
 */
public class ProductwiseTaggedPriceVersionId  implements Serializable{
    private final String productId;
    private final String taggedPriceVersionId;

    public ProductwiseTaggedPriceVersionId(String productId, String taggedPriceVersionId) {
        this.productId = productId;
        this.taggedPriceVersionId = taggedPriceVersionId;
    }

    public String getProductId() {
        return productId;
    }

    public String getTaggedPriceVersionId() {
        return taggedPriceVersionId;
    }
}
