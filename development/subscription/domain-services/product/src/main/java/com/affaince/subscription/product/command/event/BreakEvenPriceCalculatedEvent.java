package com.affaince.subscription.product.command.event;

import com.affaince.subscription.common.vo.PriceTaggedWithProduct;

import java.util.Set;
import java.util.SortedSet;

/**
 * Created by mandar on 5/22/2017.
 */
public class BreakEvenPriceCalculatedEvent {
    private String productId;
    private Set<PriceTaggedWithProduct> taggedPriceVersions;
    public BreakEvenPriceCalculatedEvent(String productId, Set<PriceTaggedWithProduct> taggedPriceVersions) {
        this.productId=productId;
        this.taggedPriceVersions=taggedPriceVersions;
    }

    public BreakEvenPriceCalculatedEvent() {
    }

    public String getProductId() {
        return productId;
    }

    public Set<PriceTaggedWithProduct> getTaggedPriceVersions() {
        return taggedPriceVersions;
    }
}
