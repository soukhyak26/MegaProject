package com.affaince.subscription.product.command.event;

import com.affaince.subscription.common.vo.ProductForecastParameter;

/**
 * Created by mandar on 06-10-2016.
 */
public class ManualForecastAddedEvent {
    private String productId;
    private ProductForecastParameter[] productForecastParameters;


    public ManualForecastAddedEvent(String productId, ProductForecastParameter[] productForecastParameters) {
        this.productId = productId;
        this.productForecastParameters = productForecastParameters;
    }

    public ManualForecastAddedEvent() {
    }

    public String getProductId() {
        return productId;
    }

    public ProductForecastParameter[] getProductForecastParameters() {
        return productForecastParameters;
    }
}
