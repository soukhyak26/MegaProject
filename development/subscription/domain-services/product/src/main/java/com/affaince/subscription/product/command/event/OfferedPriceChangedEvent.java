package com.affaince.subscription.product.command.event;

import com.affaince.subscription.product.command.domain.PriceBucket;

/**
 * Created by mandar on 10-10-2016.
 */
public class OfferedPriceChangedEvent {
    private String productId;
    private PriceBucket newPriceBucket;

    public OfferedPriceChangedEvent() {
    }
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
