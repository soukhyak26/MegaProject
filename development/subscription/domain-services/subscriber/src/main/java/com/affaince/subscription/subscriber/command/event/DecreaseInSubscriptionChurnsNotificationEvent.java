package com.affaince.subscription.subscriber.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 10/2/2017.
 */
public class DecreaseInSubscriptionChurnsNotificationEvent {
    private double expectedChangeInChurnedSubscriptionCount;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate forecastDate;

    public DecreaseInSubscriptionChurnsNotificationEvent(double expectedChangeInChurnedSubscriptionCount, LocalDate startDate, LocalDate endDate, LocalDate forecastDate) {
        this.expectedChangeInChurnedSubscriptionCount = expectedChangeInChurnedSubscriptionCount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.forecastDate = forecastDate;
    }

    public double getExpectedChangeInChurnedSubscriptionCount() {
        return expectedChangeInChurnedSubscriptionCount;
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
