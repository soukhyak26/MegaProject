package com.affaince.subscription.product.command.event;

import com.affaince.subscription.product.command.domain.PriceBucket;

/**
 * Created by mandar on 16-10-2016.
 */
public class OpeningPriceOrPercentRegisteredEvent {

    private final String productId;
    private final PriceBucket priceBucket;

    public OpeningPriceOrPercentRegisteredEvent(String productId, PriceBucket priceBucket) {
        this.productId = productId;
        this.priceBucket = priceBucket;
    }

    public String getProductId() {
        return productId;
    }

    public PriceBucket getPriceBucket() {
        return priceBucket;
    }
}
