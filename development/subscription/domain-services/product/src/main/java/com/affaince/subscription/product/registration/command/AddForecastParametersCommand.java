package com.affaince.subscription.product.registration.command;

import com.affaince.subscription.product.registration.vo.ForecastedPriceParameter;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by rbsavaliya on 05-12-2015.
 */
public class AddForecastParametersCommand {

    @TargetAggregateIdentifier
    private String productId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private List<ForecastedPriceParameter> forecastedPriceParamters;

    public AddForecastParametersCommand(String productId, LocalDate fromDate, LocalDate toDate, List<ForecastedPriceParameter> priceForecastList) {
        this.productId = productId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.forecastedPriceParamters = priceForecastList;
    }

    public AddForecastParametersCommand() {
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public void setForecastedPriceParamters(List<ForecastedPriceParameter> forecastedPriceParamters) {
        this.forecastedPriceParamters = forecastedPriceParamters;
    }

    public String getProductId() {
        return productId;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public List<ForecastedPriceParameter> getForecastedPriceParamters() {
        return this.forecastedPriceParamters;
    }

    public void addForecastedPriceParameter(ForecastedPriceParameter priceParameter) {
        this.forecastedPriceParamters.add(priceParameter);
    }
}
