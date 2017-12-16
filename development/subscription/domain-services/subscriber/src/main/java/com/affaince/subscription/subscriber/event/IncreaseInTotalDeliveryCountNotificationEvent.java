package com.affaince.subscription.subscriber.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 10/2/2017.
 */
public class IncreaseInTotalDeliveryCountNotificationEvent {
    private double expectedChangeInTotalDeliveryCount;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate forecastDate;

    public IncreaseInTotalDeliveryCountNotificationEvent(double expectedChangeInTotalDeliveryCount, LocalDate startDate, LocalDate endDate, LocalDate forecastDate) {
        this.expectedChangeInTotalDeliveryCount = expectedChangeInTotalDeliveryCount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.forecastDate = forecastDate;
    }

    public double getExpectedChangeInTotalDeliveryCount() {
        return expectedChangeInTotalDeliveryCount;
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
