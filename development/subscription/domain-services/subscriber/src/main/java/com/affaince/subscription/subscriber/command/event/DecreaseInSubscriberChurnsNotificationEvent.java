package com.affaince.subscription.subscriber.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 10/2/2017.
 */
public class DecreaseInSubscriberChurnsNotificationEvent {
    private double expectedChangeInChurnedSubscriberCount;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate forecastDate;

    public DecreaseInSubscriberChurnsNotificationEvent(double expectedChangeInChurnedSubscriberCount, LocalDate startDate, LocalDate endDate, LocalDate forecastDate) {
        this.expectedChangeInChurnedSubscriberCount = expectedChangeInChurnedSubscriberCount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.forecastDate = forecastDate;
    }

    public double getExpectedChangeInChurnedSubscriberCount() {
        return expectedChangeInChurnedSubscriberCount;
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
