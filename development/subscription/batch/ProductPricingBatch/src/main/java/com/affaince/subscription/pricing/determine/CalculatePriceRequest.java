package com.affaince.subscription.pricing.determine;

import com.affaince.subscription.common.type.ProductDemandTrend;

/**
 * Created by rsavaliya on 2/4/17.
 */
class CalculatePriceRequest {
    private final ProductDemandTrend productDemandTrend;
    private final double weight;

    public CalculatePriceRequest(ProductDemandTrend productDemandTrend, double weight) {
        this.productDemandTrend = productDemandTrend;
        this.weight = weight;
    }

    public ProductDemandTrend getProductDemandTrend() {
        return productDemandTrend;
    }

    public double getWeight() {
        return weight;
    }
}
