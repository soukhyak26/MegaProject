package com.affaince.subscription.product.vo;

import org.joda.time.LocalDateTime;

/**
 * Created by mandar on 06-07-2016.
 */
public class DemandGrowthAndChurnForecast {
    private final long forecastedNewSubscriptionCount;
    private final long forecastedChurnedSubscriptionCount;
    private final long forecastedTotalSubscriptionCount;
    private final LocalDateTime forecastStartDate;
    private final LocalDateTime forecastEndDate;

    public DemandGrowthAndChurnForecast(long forecastedNewSubscriptionCount, long forecastedChurnedSubscriptionCount, long forecastedTotalSubscriptionCount, LocalDateTime forecastStartDate, LocalDateTime forecastEndDate) {
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

    public LocalDateTime getForecastStartDate() {
        return forecastStartDate;
    }

    public LocalDateTime getForecastEndDate() {
        return forecastEndDate;
    }
}
