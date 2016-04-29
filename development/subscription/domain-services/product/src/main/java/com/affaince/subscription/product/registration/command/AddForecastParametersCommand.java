package com.affaince.subscription.product.registration.command;

import com.affaince.subscription.product.registration.vo.ForecastedPriceParameter;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 05-12-2015.
 */
public class AddForecastParametersCommand {

    @TargetAggregateIdentifier
    private String productId;
    private ForecastedPriceParameter forecastedPriceParameter;

    public AddForecastParametersCommand(String productId, ForecastedPriceParameter forecastedPriceParameter) {
        this.productId = productId;
        this.forecastedPriceParameter = forecastedPriceParameter;
    }

    public AddForecastParametersCommand() {
    }

    public String getProductId() {
        return productId;
    }

    public ForecastedPriceParameter getForecastedPriceParameter() {
        return forecastedPriceParameter;
    }

}
