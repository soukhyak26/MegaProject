package com.affaince.subscription.product.command.event;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

/**
 * Created by mandar on 14-08-2016.
 */
public class SubscriptionPseudoActualsUpdatedEvent {
    private final String productId;
    private final LocalDate forecastStartDate;
    private final LocalDateTime forecastEndDate;
    private final long newSubscriptionForecast;
    private final long churnedSubscriptionForecast;
    private final long forecastedTotalSubscriptionCount;

    public SubscriptionPseudoActualsUpdatedEvent(String productId, LocalDate forecastStartDate, LocalDateTime forecastEndDate, long newSubscriptionForecast, long churnedSubscriptionForecast, long forecastedTotalSubscriptionCount) {
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

    public LocalDateTime getForecastEndDate() {
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
