package com.affaince.subscription.product.web.request;

import com.affaince.subscription.common.type.ProductDemandTrend;

/**
 * Created by mandar on 30-01-2017.
 */
public class CalculatePriceRequest {
    private ProductDemandTrend productDemandTrend;
    private double weight;

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
