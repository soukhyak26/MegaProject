package com.affaince.subscription.product.registration.command;

import com.affaince.subscription.product.registration.vo.ForecastedPriceParameter;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.List;

/**
 * Created by rbsavaliya on 05-12-2015.
 */
public class AddForecastParametersCommand {

    @TargetAggregateIdentifier
    private String productId;
    private List<ForecastedPriceParameter> forecastedPriceParamters;

    public AddForecastParametersCommand(String productId, List<ForecastedPriceParameter> priceForecastList) {
        this.productId = productId;
        this.forecastedPriceParamters = priceForecastList;
    }

    public AddForecastParametersCommand() {
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setForecastedPriceParamters(List<ForecastedPriceParameter> forecastedPriceParamters) {
        this.forecastedPriceParamters = forecastedPriceParamters;
    }

    public String getProductId() {
        return productId;
    }

    public List<ForecastedPriceParameter> getForecastedPriceParamters() {
        return this.forecastedPriceParamters;
    }

    public void addForecastedPriceParameter(ForecastedPriceParameter priceParameter) {
        this.forecastedPriceParamters.add(priceParameter);
    }
}
