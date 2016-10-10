package com.affaince.subscription.product.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 19-06-2016.
 */
public class ProductSubscriptionUpdatedEvent {
    private final String productId;
    private final int subscriptionCount;
    private final LocalDate subscriptionActivationDate;

    public ProductSubscriptionUpdatedEvent(String productId, int productSubscriptionCount, LocalDate subscriptionActivationDate) {
        this.productId = productId;
        this.subscriptionCount = productSubscriptionCount;
        this.subscriptionActivationDate = subscriptionActivationDate;
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
