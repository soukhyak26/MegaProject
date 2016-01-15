package com.affaince.subscription.product.registration.command.event;

import com.affaince.subscription.product.registration.vo.ForecastedPriceParameter;

import java.util.List;

/**
 * Created by rbsavaliya on 26-07-2015.
 */
public class ForecastParametersAddedEvent {
    private String productId;
    private List<ForecastedPriceParameter> forecastedPriceParamters;

    public ForecastParametersAddedEvent(String productId, List<ForecastedPriceParameter> forecastedPriceParamters) {
        this.productId = productId;
        this.forecastedPriceParamters = forecastedPriceParamters;
    }

    public ForecastParametersAddedEvent() {
    }

    public String getProductId() {
        return productId;
    }

    public List<ForecastedPriceParameter> getForecastedPriceParamters() {
        return forecastedPriceParamters;
    }
}
