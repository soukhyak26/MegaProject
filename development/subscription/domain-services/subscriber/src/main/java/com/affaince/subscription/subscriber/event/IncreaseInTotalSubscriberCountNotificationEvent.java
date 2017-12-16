package com.affaince.subscription.subscriber.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 10/2/2017.
 */
public class IncreaseInTotalSubscriberCountNotificationEvent {
    private double expectedChangeInTotalSubscriberCount;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate forecastDate;

    public IncreaseInTotalSubscriberCountNotificationEvent(double expectedChangeInTotalSubscriberCount, LocalDate startDate, LocalDate endDate, LocalDate forecastDate) {
        this.expectedChangeInTotalSubscriberCount = expectedChangeInTotalSubscriberCount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.forecastDate = forecastDate;
    }

    public double getExpectedChangeInTotalSubscriberCount() {
        return expectedChangeInTotalSubscriberCount;
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
