package com.affaince.subscription.product.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 14-08-2016.
 */
public class SubscriptionPseudoActualsUpdatedEvent {
    private final String productId;
    private final LocalDate startDate;
    private final long totalSubscriptionForecast;
    private final long churnedSubscriptionForecast;

    public SubscriptionPseudoActualsUpdatedEvent(String productId, LocalDate forecastDate, long forecastedTotalSubscriptionCount, long forecastedChurnedSubscriptionCount) {
        this.productId = productId;
        this.startDate = forecastDate;
        this.totalSubscriptionForecast = forecastedTotalSubscriptionCount;
        this.churnedSubscriptionForecast = forecastedChurnedSubscriptionCount;
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
