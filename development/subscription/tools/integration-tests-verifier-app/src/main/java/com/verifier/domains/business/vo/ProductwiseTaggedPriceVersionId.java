package com.verifier.domains.business.vo;

import java.io.Serializable;

/**
 * Created by mandar on 31-12-2016.
 */
public class ProductwiseTaggedPriceVersionId implements Serializable{
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductwiseTaggedPriceVersionId that = (ProductwiseTaggedPriceVersionId) o;

        if (!productId.equals(that.productId)) return false;
        return taggedPriceVersionId.equals(that.taggedPriceVersionId);

    }

    @Override
    public int hashCode() {
        int result = productId.hashCode();
        result = 31 * result + taggedPriceVersionId.hashCode();
        return result;
    }
}
