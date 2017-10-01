package com.affaince.subscription.product.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 10/1/2017.
 */
public class ChangeOfTotalStockNotificationEvent {
    private String productId;
    private double expectedChangeInTotalSubscriptionCount;
    private LocalDate fromDate;
    private LocalDate endDate;
    private LocalDate notificationDate;

    public ChangeOfTotalStockNotificationEvent(String productId, double expectedChangeInTotalSubscriptionCount, LocalDate fromDate, LocalDate endDate, LocalDate notificationDate) {
        this.productId = productId;
        this.expectedChangeInTotalSubscriptionCount = expectedChangeInTotalSubscriptionCount;
        this.fromDate = fromDate;
        this.endDate = endDate;
        this.notificationDate = notificationDate;
    }

    public String getProductId() {
        return productId;
    }

    public double getExpectedChangeInTotalSubscriptionCount() {
        return expectedChangeInTotalSubscriptionCount;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getNotificationDate() {
        return notificationDate;
    }
}
