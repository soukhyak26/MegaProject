package com.affaince.subscription.product.command.event;

import java.util.Map;

/**
 * Created by mandar on 29-12-2016.
 */
public class SubscriptionActivitySummaryEvent {
    private String productId;
    private Map<String, Integer> subscribedItems;

    public SubscriptionActivitySummaryEvent(String productId, Map<String, Integer> subscribedItems) {
        this.productId = productId;
        this.subscribedItems = subscribedItems;
    }

    public SubscriptionActivitySummaryEvent() {
    }

    public String getProductId() {
        return productId;
    }

    public Map<String, Integer> getSubscribedItems() {
        return subscribedItems;
    }
}
