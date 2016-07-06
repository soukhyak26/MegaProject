package com.affaince.subscription.product.command.event;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by mandar on 06-07-2016.
 */
public class SubscriptionForecastUpdatedEvent {
    private final String productId;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final long totalSubscriptionForecast;
    private final long churnedSubscriptionForecast;
    @Value("${subscription.forecast.period}")
    private String forecastPeriod;

    public SubscriptionForecastUpdatedEvent(String productId, LocalDate startDate, long totalSubscriptionForecast, long churnedSubscriptionForecast) {
        this.productId = productId;
        this.startDate = startDate;
        this.totalSubscriptionForecast = totalSubscriptionForecast;
        this.churnedSubscriptionForecast = churnedSubscriptionForecast;
        if (forecastPeriod.equals("WEEK")) {
            this.endDate = startDate.plusDays(7);
        } else if (forecastPeriod.equals("MONTH")) {
            this.endDate = startDate.plusDays(daysOfMonth(startDate));
        } else {
            this.endDate = startDate.plusDays(1);
        }
    }

    private int daysOfMonth(LocalDate dt) {
        int month = dt.getMonthOfYear();
        int month2 = month;
        int days = dt.dayOfMonth().get();
        LocalDate dt2 = dt;
        while (month == month2) {
            days++;
            dt2.plusDays(1);
            month2 = dt2.getMonthOfYear();
        }
        return (days - 1);
    }

    public String getProductId() {
        return productId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public long getTotalSubscriptionForecast() {
        return totalSubscriptionForecast;
    }

    public long getChurnedSubscriptionForecast() {
        return churnedSubscriptionForecast;
    }

    public String getForecastPeriod() {
        return forecastPeriod;
    }
}
