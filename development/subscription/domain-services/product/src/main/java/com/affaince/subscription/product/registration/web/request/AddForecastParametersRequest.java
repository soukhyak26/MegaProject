package com.affaince.subscription.product.registration.web.request;

import com.affaince.subscription.product.registration.vo.ForecastedPriceParameter;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by rbsavaliya on 05-12-2015.
 */
public class AddForecastParametersRequest {
    private String productId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private List<ForecastedPriceParameter> forecastedPriceParameters;


    public AddForecastParametersRequest() {
    }

    public List<ForecastedPriceParameter> getForecastedPriceParameters() {
        return this.forecastedPriceParameters;
    }

    public void setForecastedPriceParameters(List<ForecastedPriceParameter> forecastedPriceParameters) {
        this.forecastedPriceParameters = forecastedPriceParameters;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

}
