package com.affaince.subscription.business.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 10/7/2017.
 */
public class ProductDemandDecreaseNotificationEvent {
    private String productId;
    private double referenceNewSubscriptionCount;
    private double expectedChangeInNewSubscriptionCount;
    private double referenceChurnedSubscriptionCount;
    private double expectedChangeInChurnedSubscriptionCount;
    private LocalDate fromDate;
    private LocalDate endDate;
    private LocalDate forecastDate;

    public ProductDemandDecreaseNotificationEvent(String productId,double referenceNewSubscriptionCount, double expectedChangeInNewSubscriptionCount,double referenceChurnedSubscriptionCount, double expectedChangeInChurnedSubscriptionCount, LocalDate fromDate, LocalDate endDate, LocalDate forecastDate) {
        this.productId = productId;
        this.referenceNewSubscriptionCount=referenceNewSubscriptionCount;
        this.expectedChangeInNewSubscriptionCount = expectedChangeInNewSubscriptionCount;
        this.referenceChurnedSubscriptionCount=referenceChurnedSubscriptionCount;
        this.expectedChangeInChurnedSubscriptionCount = expectedChangeInChurnedSubscriptionCount;
        this.fromDate = fromDate;
        this.endDate = endDate;
        this.forecastDate = forecastDate;
    }

    public double getReferenceNewSubscriptionCount() {
        return referenceNewSubscriptionCount;
    }

    public double getReferenceChurnedSubscriptionCount() {
        return referenceChurnedSubscriptionCount;
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
