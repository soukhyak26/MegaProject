package com.affaince.subscription.subscriber.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 10/2/2017.
 */
public class IncreaseInTotalSubscriptionCountNotificationEvent {
    private double expectedChangeInTotalSubscriptionCount;
    private  LocalDate startDate;
    private  LocalDate endDate;
    private LocalDate forecastDate;

    public IncreaseInTotalSubscriptionCountNotificationEvent(double expectedChangeInTotalSubscriptionCount, LocalDate startDate, LocalDate endDate, LocalDate forecastDate) {
        this.expectedChangeInTotalSubscriptionCount = expectedChangeInTotalSubscriptionCount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.forecastDate = forecastDate;
    }

    public double getExpectedChangeInTotalSubscriptionCount() {
        return expectedChangeInTotalSubscriptionCount;
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
