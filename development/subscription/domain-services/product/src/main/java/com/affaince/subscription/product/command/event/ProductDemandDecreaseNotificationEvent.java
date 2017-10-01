package com.affaince.subscription.product.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 10/1/2017.
 */
public class ProductDemandDecreaseNotificationEvent {
    private String productId;
    private double expectedChangeInNewSubscriptionCount;
    private double expectedChangeInChurnedSubscriptionCount;
    private LocalDate fromDate;
    private LocalDate endDate;
    private LocalDate forecastDate;

    public ProductDemandDecreaseNotificationEvent(String productId, double expectedChangeInNewSubscriptionCount, double expectedChangeInChurnedSubscriptionCount, LocalDate fromDate, LocalDate endDate, LocalDate forecastDate) {
        this.productId = productId;
        this.expectedChangeInNewSubscriptionCount = expectedChangeInNewSubscriptionCount;
        this.expectedChangeInChurnedSubscriptionCount = expectedChangeInChurnedSubscriptionCount;
        this.fromDate = fromDate;
        this.endDate = endDate;
        this.forecastDate = forecastDate;
    }

    public String getProductId() {
        return productId;
    }

    public double getExpectedChangeInNewSubscriptionCount() {
        return expectedChangeInNewSubscriptionCount;
    }

    public double getExpectedChangeInChurnedSubscriptionCount() {
        return expectedChangeInChurnedSubscriptionCount;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getForecastDate() {
        return forecastDate;
    }
}
