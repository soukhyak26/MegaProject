package com.affaince.subscription.common.type;

/**
 * Created by mandar on 30-01-2017.
 */
public class WeightedProductDemandTrend {
    private final ProductDemandTrend productDemandTrend;
    private final double weight;

    public WeightedProductDemandTrend(ProductDemandTrend productDemandTrend, double weight) {
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
