package com.affaince.subscription.product.command.event;

import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.product.command.domain.PriceBucket;

public class OfferedPriceRecommendedEvent {
    private String productId;
    private PriceBucket newPriceBucket;
    private ProductDemandTrend productDemandTrend;
    public OfferedPriceRecommendedEvent() {
    }

    public OfferedPriceRecommendedEvent(String productId, PriceBucket priceBucket,ProductDemandTrend productDemandTrend) {
        this.productId = productId;
        this.newPriceBucket = priceBucket;
        this.productDemandTrend=productDemandTrend;
    }

    public String getProductId() {
        return productId;
    }

    public ProductDemandTrend getProductDemandTrend() {
        return productDemandTrend;
    }

    public PriceBucket getNewPriceBucket() {
        return newPriceBucket;
    }


}
