package com.affaince.subscription.subscriber.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 10/2/2017.
 */
public class DecreaseInNewSubscriptionsNotificationEvent {
    private double expectedChangeInNewSubscriptionCount;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate forecastDate;

    public DecreaseInNewSubscriptionsNotificationEvent(double expectedChangeInNewSubscriptionCount, LocalDate startDate, LocalDate endDate, LocalDate forecastDate) {
        this.expectedChangeInNewSubscriptionCount = expectedChangeInNewSubscriptionCount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.forecastDate = forecastDate;
    }

    public double getExpectedChangeInNewSubscriptionCount() {
        return expectedChangeInNewSubscriptionCount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getForecastDate() {
        return forecastDate;
    }
}
