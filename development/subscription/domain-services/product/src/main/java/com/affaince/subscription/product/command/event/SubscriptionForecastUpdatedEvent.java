package com.affaince.subscription.product.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 06-07-2016.
 */
public class SubscriptionForecastUpdatedEvent {
    private String productId;
    private LocalDate forecastStartDate;
    private LocalDate forecastEndDate;
    private long newSubscriptionForecast;
    private long churnedSubscriptionForecast;
    private long forecastedTotalSubscriptionCount;
    private LocalDate forecastDate;
    public SubscriptionForecastUpdatedEvent(String productId, LocalDate forecastStartDate, LocalDate forecastEndDate, long newSubscriptionForecast, long churnedSubscriptionForecast, long forecastedTotalSubscriptionCount,LocalDate forecastDate) {
        this.productId = productId;
        this.forecastStartDate = forecastStartDate;
        this.forecastEndDate = forecastEndDate;
        this.newSubscriptionForecast = newSubscriptionForecast;
        this.churnedSubscriptionForecast = churnedSubscriptionForecast;
        this.forecastedTotalSubscriptionCount = forecastedTotalSubscriptionCount;
        this.forecastDate=forecastDate;
    }

    public SubscriptionForecastUpdatedEvent() {
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

    public LocalDate getForecastDate() {
        return forecastDate;
    }
}
