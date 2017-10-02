package com.affaince.subscription.subscriber.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 10/2/2017.
 */
public class DecreaseInTotalDeliveryCountNotificationEvent {
    private double expectedChangeInTotalDeliveryCount;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate forecastDate;

    public DecreaseInTotalDeliveryCountNotificationEvent(double expectedChangeInTotalDeliveryCount, LocalDate startDate, LocalDate endDate, LocalDate forecastDate) {
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
