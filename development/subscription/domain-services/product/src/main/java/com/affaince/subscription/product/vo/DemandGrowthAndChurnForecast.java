package com.affaince.subscription.product.vo;

/**
 * Created by mandar on 06-07-2016.
 */
public class DemandGrowthAndChurnForecast {
    private final long forecastedTotalSubscriptionCount;
    private final long ForecastedChurnedSubscriptionCount;

    public DemandGrowthAndChurnForecast(long forecastedTotalSubscriptionCount, long forecastedChurnedSubscriptionCount) {
        this.forecastedTotalSubscriptionCount = forecastedTotalSubscriptionCount;
        ForecastedChurnedSubscriptionCount = forecastedChurnedSubscriptionCount;
    }

    public long getForecastedTotalSubscriptionCount() {
        return forecastedTotalSubscriptionCount;
    }

    public long getForecastedChurnedSubscriptionCount() {
        return ForecastedChurnedSubscriptionCount;
    }
}
