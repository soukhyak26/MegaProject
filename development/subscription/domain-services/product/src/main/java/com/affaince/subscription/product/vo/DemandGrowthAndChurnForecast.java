package com.affaince.subscription.product.vo;

import org.joda.time.LocalDateTime;

import java.util.List;

/**
 * Created by mandar on 06-07-2016.
 */
public class DemandGrowthAndChurnForecast {
    private final List<Double> forecastedNewSubscriptionCount;
    private final List<Double> forecastedChurnedSubscriptionCount;
    private final List<Double> forecastedTotalSubscriptionCount;
    private final LocalDateTime forecastStartDate;
    private final LocalDateTime forecastEndDate;

    public DemandGrowthAndChurnForecast(List<Double> forecastedNewSubscriptionCount, List<Double> forecastedChurnedSubscriptionCount, List<Double> forecastedTotalSubscriptionCount, LocalDateTime forecastStartDate, LocalDateTime forecastEndDate) {
        this.forecastedNewSubscriptionCount = forecastedNewSubscriptionCount;
        this.forecastedChurnedSubscriptionCount = forecastedChurnedSubscriptionCount;
        this.forecastedTotalSubscriptionCount = forecastedTotalSubscriptionCount;
        this.forecastStartDate = forecastStartDate;
        this.forecastEndDate = forecastEndDate;
    }

    public List<Double> getForecastedNewSubscriptionCount() {
        return forecastedNewSubscriptionCount;
    }

    public List<Double> getForecastedChurnedSubscriptionCount() {
        return forecastedChurnedSubscriptionCount;
    }

    public List<Double> getForecastedTotalSubscriptionCount() {
        return forecastedTotalSubscriptionCount;
    }

    public LocalDateTime getForecastStartDate() {
        return forecastStartDate;
    }

    public LocalDateTime getForecastEndDate() {
        return forecastEndDate;
    }
}
