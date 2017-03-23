package com.affaince.subscription.product.command.event;

import org.joda.time.LocalDate;

import java.util.Map;

/**
 * Created by mandar on 29-12-2016.
 */
public class SubscriptionActivitySummaryEvent {
    private String productId;
    private Map<String, Integer> subscribedItems;
    private LocalDate subscriptionChangedDate;

    public SubscriptionActivitySummaryEvent(String productId, Map<String, Integer> subscribedItems, LocalDate subscriptionChangedDate) {
        this.productId = productId;
        this.subscribedItems = subscribedItems;
        this.subscriptionChangedDate = subscriptionChangedDate;
    }

    public SubscriptionActivitySummaryEvent() {
    }

    public String getProductId() {
        return productId;
    }

    public Map<String, Integer> getSubscribedItems() {
        return subscribedItems;
    }

    public LocalDate getSubscriptionChangedDate() {
        return subscriptionChangedDate;
    }
}
