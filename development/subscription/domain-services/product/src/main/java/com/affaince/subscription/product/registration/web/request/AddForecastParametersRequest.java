package com.affaince.subscription.product.registration.web.request;

import com.affaince.subscription.product.registration.vo.ForecastedPriceParameter;

/**
 * Created by rbsavaliya on 05-12-2015.
 */
public class AddForecastParametersRequest {
    private String productId;
    private ForecastedPriceParameter forecastedPriceParameter;
    private double demandDensity;
    private double averageDemandPerSubscriber;
    private long totalDeliveriesPerPeriod;
    private double averageWeightPerDelivery;

    public AddForecastParametersRequest() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public ForecastedPriceParameter getForecastedPriceParameter() {
        return forecastedPriceParameter;
    }

    public void setForecastedPriceParameter(ForecastedPriceParameter forecastedPriceParameter) {
        this.forecastedPriceParameter = forecastedPriceParameter;
    }

    public double getDemandDensity() {
        return demandDensity;
    }

    public void setDemandDensity(double demandDensity) {
        this.demandDensity = demandDensity;
    }

    public double getAverageDemandPerSubscriber() {
        return averageDemandPerSubscriber;
    }

    public void setAverageDemandPerSubscriber(double averageDemandPerSubscriber) {
        this.averageDemandPerSubscriber = averageDemandPerSubscriber;
    }

    public long getTotalDeliveriesPerPeriod() {
        return totalDeliveriesPerPeriod;
    }

    public void setTotalDeliveriesPerPeriod(long totalDeliveriesPerPeriod) {
        this.totalDeliveriesPerPeriod = totalDeliveriesPerPeriod;
    }

    public double getAverageWeightPerDelivery() {
        return averageWeightPerDelivery;
    }

    public void setAverageWeightPerDelivery(double averageWeightPerDelivery) {
        this.averageWeightPerDelivery = averageWeightPerDelivery;
    }
}
