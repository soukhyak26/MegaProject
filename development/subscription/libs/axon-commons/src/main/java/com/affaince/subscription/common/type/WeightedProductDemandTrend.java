package com.affaince.subscription.common.type;

/**
 * Created by mandar on 30-01-2017.
 */
public class WeightedProductDemandTrend {
    private final ProductDemandTrend productDemandTrend;
    private final double weight;
    private final String productId;

    public WeightedProductDemandTrend(ProductDemandTrend productDemandTrend, double weight, String productId) {
        this.productDemandTrend = productDemandTrend;
        this.weight = weight;
        this.productId = productId;
    }

    public ProductDemandTrend getProductDemandTrend() {
        return productDemandTrend;
    }

    public double getWeight() {
        return weight;
    }

    public String getProductId() {
        return productId;
    }
}
