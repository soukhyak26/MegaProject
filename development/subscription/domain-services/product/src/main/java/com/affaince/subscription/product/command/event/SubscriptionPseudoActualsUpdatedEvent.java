package com.affaince.subscription.product.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 14-08-2016.
 */
public class SubscriptionPseudoActualsUpdatedEvent {
    private String productId;
    private LocalDate forecastStartDate;
    private LocalDate forecastEndDate;
    private long newSubscriptionForecast;
    private long churnedSubscriptionForecast;
    private long forecastedTotalSubscriptionCount;

    public SubscriptionPseudoActualsUpdatedEvent(String productId, LocalDate forecastStartDate, LocalDate forecastEndDate, long newSubscriptionForecast, long churnedSubscriptionForecast, long forecastedTotalSubscriptionCount) {
        this.productId = productId;
        this.forecastStartDate = forecastStartDate;
        this.forecastEndDate = forecastEndDate;
        this.newSubscriptionForecast = newSubscriptionForecast;
        this.churnedSubscriptionForecast = churnedSubscriptionForecast;
        this.forecastedTotalSubscriptionCount = forecastedTotalSubscriptionCount;
    }

    public SubscriptionPseudoActualsUpdatedEvent() {
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
