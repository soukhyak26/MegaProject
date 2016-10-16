package com.affaince.subscription.product.command.event;

import com.affaince.subscription.product.command.domain.PriceBucket;

public class OfferedPriceRecommendedEvent {
    private String productId;
    private PriceBucket newPriceBucket;

    public OfferedPriceRecommendedEvent() {
    }

    public OfferedPriceRecommendedEvent(String productId, PriceBucket priceBucket) {
        this.productId = productId;
        this.newPriceBucket = priceBucket;
    }

    public String getProductId() {
        return productId;
    }

    public PriceBucket getNewPriceBucket() {
        return newPriceBucket;
    }
}