package com.affaince.subscription.product.event;

import com.affaince.subscription.common.vo.ProductForecastParameter;

/**
 * Created by rbsavaliya on 26-07-2015.
 */
public class ForecastParametersAddedEvent {
    private String productId;
    private ProductForecastParameter productForecastParameter;

    public ForecastParametersAddedEvent(String productId, ProductForecastParameter productForecastParameter) {
        this.productId = productId;
        this.productForecastParameter = productForecastParameter;
    }

    public ForecastParametersAddedEvent() {
    }

    public String getProductId() {
        return productId;
    }

    public ProductForecastParameter getProductForecastParameter() {
        return productForecastParameter;
    }

}