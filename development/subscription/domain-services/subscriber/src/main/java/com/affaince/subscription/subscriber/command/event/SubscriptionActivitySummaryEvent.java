package com.affaince.subscription.subscriber.command.event;

import java.util.Map;

/**
 * Created by rbsavaliya on 28-12-2016.
 */
public class SubscriptionActivitySummaryEvent {

    private String productId;
    private Map<String, Integer> subscribedItems;

    public SubscriptionActivitySummaryEvent(String productId, Map<String, Integer> subscribedItems) {
        this.productId = productId;
        this.subscribedItems = subscribedItems;
    }

    public String getProductId() {
        return productId;
    }

    public Map<String, Integer> getSubscribedItems() {
        return subscribedItems;
    }
}
