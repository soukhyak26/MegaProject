package com.affaince.subscription.subscriber.event;

import org.joda.time.LocalDate;

import java.util.Map;

/**
 * Created by rbsavaliya on 28-12-2016.
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
