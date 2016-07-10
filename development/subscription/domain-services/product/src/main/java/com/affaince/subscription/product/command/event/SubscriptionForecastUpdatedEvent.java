package com.affaince.subscription.product.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 06-07-2016.
 */
public class SubscriptionForecastUpdatedEvent {
    private final String productId;
    private final LocalDate startDate;
    private final long totalSubscriptionForecast;
    private final long churnedSubscriptionForecast;

    public SubscriptionForecastUpdatedEvent(String productId, LocalDate startDate, long totalSubscriptionForecast, long churnedSubscriptionForecast) {
        this.productId = productId;
        this.startDate = startDate;
        this.totalSubscriptionForecast = totalSubscriptionForecast;
        this.churnedSubscriptionForecast = churnedSubscriptionForecast;

    }

    public String getProductId() {
        return productId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public long getTotalSubscriptionForecast() {
        return totalSubscriptionForecast;
    }

    public long getChurnedSubscriptionForecast() {
        return churnedSubscriptionForecast;
    }
}
