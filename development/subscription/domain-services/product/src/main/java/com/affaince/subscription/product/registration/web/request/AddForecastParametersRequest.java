package com.affaince.subscription.product.registration.web.request;

import com.affaince.subscription.product.registration.vo.ForecastedPriceParameter;

import java.util.List;

/**
 * Created by rbsavaliya on 05-12-2015.
 */
public class AddForecastParametersRequest {
    private String productId;
    private List<ForecastedPriceParameter> forecastedPriceParameters;


    public AddForecastParametersRequest() {
    }

    public List<ForecastedPriceParameter> getForecastedPriceParameters() {
        return this.forecastedPriceParameters;
    }

    public void setForecastedPriceParameters(List<ForecastedPriceParameter> forecastedPriceParameters) {
        this.forecastedPriceParameters = forecastedPriceParameters;
    }


    public void setProductId(String productId) {
        this.productId = productId;
    }


    public String getProductId() {
        return productId;
    }


}
