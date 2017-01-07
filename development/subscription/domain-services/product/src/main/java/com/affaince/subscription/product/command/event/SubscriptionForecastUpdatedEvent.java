package com.affaince.subscription.product.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 06-07-2016.
 */
public class SubscriptionForecastUpdatedEvent {
    private final String productId;
    private final LocalDate forecastStartDate;
    private final LocalDate forecastEndDate;
    private final long newSubscriptionForecast;
    private final long churnedSubscriptionForecast;
    private final long forecastedTotalSubscriptionCount;

    public SubscriptionForecastUpdatedEvent(String productId, LocalDate forecastStartDate, LocalDate forecastEndDate, long newSubscriptionForecast, long churnedSubscriptionForecast, long forecastedTotalSubscriptionCount) {
        this.productId = productId;
        this.forecastStartDate = forecastStartDate;
        this.forecastEndDate = forecastEndDate;
        this.newSubscriptionForecast = newSubscriptionForecast;
        this.churnedSubscriptionForecast = churnedSubscriptionForecast;
        this.forecastedTotalSubscriptionCount = forecastedTotalSubscriptionCount;
    }

    public String getProductId() {
        return productId;
    }

    public LocalDate getForecastStartDate() {
        return forecastStartDate;
    }

    public LocalDate getForecastEndDate() {
        return forecastEndDate;
    }

    public long getNewSubscriptionForecast() {
        return newSubscriptionForecast;
    }

    public long getChurnedSubscriptionForecast() {
        return churnedSubscriptionForecast;
    }

    public long getForecastedTotalSubscriptionCount() {
        return forecastedTotalSubscriptionCount;
    }
}
