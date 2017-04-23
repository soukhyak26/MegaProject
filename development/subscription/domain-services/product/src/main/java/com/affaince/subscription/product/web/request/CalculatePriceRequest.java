package com.affaince.subscription.product.web.request;

import com.affaince.subscription.common.type.ProductDemandTrend;

/**
 * Created by mandar on 30-01-2017.
 */
public class CalculatePriceRequest {
    private ProductDemandTrend productDemandTrend;
    private double weight;

    public CalculatePriceRequest() {
    }

    public ProductDemandTrend getProductDemandTrend() {
        return productDemandTrend;
    }

    public void setProductDemandTrend(ProductDemandTrend productDemandTrend) {
        this.productDemandTrend = productDemandTrend;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
