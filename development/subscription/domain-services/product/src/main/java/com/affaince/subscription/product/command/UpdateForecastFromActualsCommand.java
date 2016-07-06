package com.affaince.subscription.product.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.joda.time.LocalDate;

/**
 * Created by mandark on 29-04-2016.
 */
public class UpdateForecastFromActualsCommand {
    @TargetAggregateIdentifier
    private final String productId;
    private final LocalDate forecastDate;
    private final long totalSubscriptionsForecast;
    private final long churnedSubscriptionsForecast;

    public UpdateForecastFromActualsCommand(String productId, LocalDate forecastDate, long totalSubscriptionsForecast, long churnedSubscriptionsForecast) {
        this.productId = productId;
        this.forecastDate = forecastDate;
        this.totalSubscriptionsForecast = totalSubscriptionsForecast;
        this.churnedSubscriptionsForecast = churnedSubscriptionsForecast;
    }

    public String getProductId() {
        return productId;
    }

    public LocalDate getForecastDate() {
        return forecastDate;
    }

    public long getTotalSubscriptionsForecast() {
        return totalSubscriptionsForecast;
    }

    public long getChurnedSubscriptionsForecast() {
        return churnedSubscriptionsForecast;
    }
}
