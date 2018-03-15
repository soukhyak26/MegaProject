package com.verifier.domains.product.vo;

import org.joda.time.LocalDateTime;

/**
 * Created by mandar on 06-07-2016.
 */
public class DemandGrowthAndChurnForecast {
    private final Double forecastedNewSubscriptionCount;
    private final Double forecastedChurnedSubscriptionCount;
    private final Double forecastedTotalSubscriptionCount;
    private final LocalDateTime forecastStartDate;
    private final LocalDateTime forecastEndDate;

    public DemandGrowthAndChurnForecast(Double forecastedNewSubscriptionCount, Double forecastedChurnedSubscriptionCount, Double forecastedTotalSubscriptionCount, LocalDateTime forecastStartDate, LocalDateTime forecastEndDate) {
        this.forecastedNewSubscriptionCount = forecastedNewSubscriptionCount;
        this.forecastedChurnedSubscriptionCount = forecastedChurnedSubscriptionCount;
        this.forecastedTotalSubscriptionCount = forecastedTotalSubscriptionCount;
        this.forecastStartDate = forecastStartDate;
        this.forecastEndDate = forecastEndDate;
    }

    public Double getForecastedNewSubscriptionCount() {
        return forecastedNewSubscriptionCount;
    }

    public Double getForecastedChurnedSubscriptionCount() {
        return forecastedChurnedSubscriptionCount;
    }

    public Double getForecastedTotalSubscriptionCount() {
        return forecastedTotalSubscriptionCount;
    }

    public LocalDateTime getForecastStartDate() {
        return forecastStartDate;
    }

    public LocalDateTime getForecastEndDate() {
        return forecastEndDate;
    }
}
