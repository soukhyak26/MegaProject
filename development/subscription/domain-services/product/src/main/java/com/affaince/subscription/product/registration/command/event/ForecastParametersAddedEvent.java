package com.affaince.subscription.product.registration.command.event;

import com.affaince.subscription.product.registration.vo.ForecastedPriceParameter;

/**
 * Created by rbsavaliya on 26-07-2015.
 */
public class ForecastParametersAddedEvent {
    private String productId;
    private ForecastedPriceParameter forecastedPriceParamter;
    private double demandDensity;
    private double averageDemandPerSubscriber;
    private long totalDeliveriesPerPeriod;
    private double averageWeightPerDelivery;

    public ForecastParametersAddedEvent(String productId, ForecastedPriceParameter forecastedPriceParamter, double demandDensity, double averageDemandPerSubscriber, long totalDeliveriesPerPeriod, double averageWeightPerDelivery) {
        this.productId = productId;
        this.forecastedPriceParamter = forecastedPriceParamter;
        this.demandDensity = demandDensity;
        this.averageDemandPerSubscriber = averageDemandPerSubscriber;
        this.totalDeliveriesPerPeriod = totalDeliveriesPerPeriod;
        this.averageWeightPerDelivery = averageWeightPerDelivery;
    }

    public ForecastParametersAddedEvent() {
    }

    public String getProductId() {
        return productId;
    }

    public ForecastedPriceParameter getForecastedPriceParamter() {
        return forecastedPriceParamter;
    }

    public double getDemandDensity() {
        return demandDensity;
    }

    public double getAverageDemandPerSubscriber() {
        return averageDemandPerSubscriber;
    }

    public long getTotalDeliveriesPerPeriod() {
        return totalDeliveriesPerPeriod;
    }

    public double getAverageWeightPerDelivery() {
        return averageWeightPerDelivery;
    }
}
