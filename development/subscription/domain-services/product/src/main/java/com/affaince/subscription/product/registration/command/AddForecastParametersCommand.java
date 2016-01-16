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
    private double demandDensity;
    private double averageDemandPerSubscriber;
    private long totalDeliveriesPerPeriod;
    private double averageWeightPerDelivery;

    public AddForecastParametersCommand(String productId, ForecastedPriceParameter forecastedPriceParameter, double demandDensity, double averageDemandPerSubscriber, long totalDeliveriesPerPeriod, double averageWeightPerDelivery) {
        this.productId = productId;
        this.forecastedPriceParameter = forecastedPriceParameter;
        this.demandDensity = demandDensity;
        this.averageDemandPerSubscriber = averageDemandPerSubscriber;
        this.totalDeliveriesPerPeriod = totalDeliveriesPerPeriod;
        this.averageWeightPerDelivery = averageWeightPerDelivery;
    }

    public AddForecastParametersCommand() {
    }

    public String getProductId() {
        return productId;
    }

    public ForecastedPriceParameter getForecastedPriceParameter() {
        return forecastedPriceParameter;
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
