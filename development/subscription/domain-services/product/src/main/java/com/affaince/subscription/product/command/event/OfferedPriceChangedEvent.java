package com.affaince.subscription.product.command.event;

import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.product.command.domain.PriceBucket;

/**
 * Created by mandar on 10-10-2016.
 */
public class OfferedPriceChangedEvent {
    private String productId;
    private PriceBucket newPriceBucket;
    private ProductDemandTrend productDemandTrend;

    public OfferedPriceChangedEvent() {
    }
    public OfferedPriceChangedEvent(String productId, PriceBucket newPriceBucket,ProductDemandTrend productDemandTrend) {
        this.productId = productId;
        this.newPriceBucket = newPriceBucket;
        this.productDemandTrend=productDemandTrend;
    }

    public String getProductId() {
        return productId;
    }

    public PriceBucket getNewPriceBucket() {
        return newPriceBucket;
    }

    public ProductDemandTrend getProductDemandTrend() {
        return productDemandTrend;
    }
}
