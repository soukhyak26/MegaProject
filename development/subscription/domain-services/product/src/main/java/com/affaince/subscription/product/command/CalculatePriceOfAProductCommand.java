package com.affaince.subscription.product.command;

import com.affaince.subscription.common.type.ProductDemandTrend;

/**
 * Created by mandar on 17-08-2016.
 */
public class CalculatePriceOfAProductCommand {
    private String productId;
    private ProductDemandTrend productDemandTrend;

    public CalculatePriceOfAProductCommand(String productId, ProductDemandTrend productDemandTrend) {
        this.productId = productId;
        this.productDemandTrend = productDemandTrend;
    }

    public String getProductId() {
        return productId;
    }

    public ProductDemandTrend getProductDemandTrend() {
        return productDemandTrend;
    }
}
