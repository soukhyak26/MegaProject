package com.affaince.subscription.product.command.event;

import com.affaince.subscription.product.vo.ForecastedPriceParameter;

/**
 * Created by rbsavaliya on 26-07-2015.
 */
public class ForecastParametersAddedEvent {
    private String productId;
    private ForecastedPriceParameter forecastedPriceParameter;

    public ForecastParametersAddedEvent(String productId, ForecastedPriceParameter forecastedPriceParameter) {
        this.productId = productId;
        this.forecastedPriceParameter = forecastedPriceParameter;
    }

    public ForecastParametersAddedEvent() {
    }

    public String getProductId() {
        return productId;
    }

    public ForecastedPriceParameter getForecastedPriceParameter() {
        return forecastedPriceParameter;
    }

}
