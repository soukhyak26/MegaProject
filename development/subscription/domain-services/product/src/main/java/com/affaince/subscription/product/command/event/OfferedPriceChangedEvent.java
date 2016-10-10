package com.affaince.subscription.product.command.event;

import com.affaince.subscription.product.command.domain.PriceBucket;

/**
 * Created by mandar on 10-10-2016.
 */
public class OfferedPriceChangedEvent {
    private final String productId;
    private final PriceBucket newPriceBucket;

    public OfferedPriceChangedEvent(String productId, PriceBucket newPriceBucket) {
        this.productId = productId;
        this.newPriceBucket = newPriceBucket;
    }

    public String getProductId() {
        return productId;
    }

    public PriceBucket getNewPriceBucket() {
        return newPriceBucket;
    }
}
