package com.affaince.subscription.subscriber.command.event;

import org.joda.time.LocalDate;

/**
 * Created by rbsavaliya on 10-01-2016.
 */
public class ProductSubscriptionActivatedEvent {
    private String productId;
    private int subscriptionCount;
    private LocalDate subscriptionActivationDate;

    public ProductSubscriptionActivatedEvent(String productId, int subscriptionCount, LocalDate subscriptionActivationDate) {
        this.productId = productId;
        this.subscriptionCount = subscriptionCount;
        this.subscriptionActivationDate = subscriptionActivationDate;
    }

    public ProductSubscriptionActivatedEvent() {
    }

    public String getProductId() {
        return productId;
    }

    public int getSubscriptionCount() {
        return subscriptionCount;
    }

    public LocalDate getSubscriptionActivationDate() {
        return subscriptionActivationDate;
    }
}
