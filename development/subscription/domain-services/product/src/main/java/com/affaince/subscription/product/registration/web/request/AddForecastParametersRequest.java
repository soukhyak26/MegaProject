package com.affaince.subscription.product.registration.web.request;

import com.affaince.subscription.product.registration.vo.ForecastedPriceParameter;

/**
 * Created by rbsavaliya on 05-12-2015.
 */
public class AddForecastParametersRequest {
    private ForecastedPriceParameter [] forecastedPriceParameters;

    public AddForecastParametersRequest() {
    }

    public ForecastedPriceParameter[] getForecastedPriceParameters() {
        return this.forecastedPriceParameters;
    }
}
