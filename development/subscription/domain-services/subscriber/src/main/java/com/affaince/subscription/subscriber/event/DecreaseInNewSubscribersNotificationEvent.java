package com.affaince.subscription.subscriber.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 10/2/2017.
 */
public class DecreaseInNewSubscribersNotificationEvent {
    private double expectedChangeInNewSubscriberCount;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate forecastDate;

    public DecreaseInNewSubscribersNotificationEvent(double expectedChangeInNewSubscriberCount, LocalDate startDate, LocalDate endDate, LocalDate forecastDate) {
        this.expectedChangeInNewSubscriberCount = expectedChangeInNewSubscriberCount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.forecastDate = forecastDate;
    }

    public double getExpectedChangeInNewSubscriberCount() {
        return expectedChangeInNewSubscriberCount;
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
