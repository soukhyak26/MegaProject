package com.affaince.subscription.product.vo;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 06-07-2016.
 */
public class DemandGrowthAndChurnForecast {
    private final long forecastedNewSubscriptionCount;
    private final long forecastedChurnedSubscriptionCount;
    private final long forecastedTotalSubscriptionCount;
    private final LocalDate forecastStartDate;
    private final LocalDate forecastEndDate;

    public DemandGrowthAndChurnForecast(long forecastedNewSubscriptionCount, long forecastedChurnedSubscriptionCount, long forecastedTotalSubscriptionCount, LocalDate forecastStartDate, LocalDate forecastEndDate) {
        this.forecastedNewSubscriptionCount = forecastedNewSubscriptionCount;
        this.forecastedChurnedSubscriptionCount = forecastedChurnedSubscriptionCount;
        this.forecastedTotalSubscriptionCount = forecastedTotalSubscriptionCount;
        this.forecastStartDate = forecastStartDate;
        this.forecastEndDate = forecastEndDate;
    }

    public long getForecastedNewSubscriptionCount() {
        return forecastedNewSubscriptionCount;
    }

    public long getForecastedChurnedSubscriptionCount() {
        return forecastedChurnedSubscriptionCount;
    }

    public long getForecastedTotalSubscriptionCount() {
        return forecastedTotalSubscriptionCount;
    }

    public LocalDate getForecastStartDate() {
        return forecastStartDate;
    }

    public LocalDate getForecastEndDate() {
        return forecastEndDate;
    }
}
