package com.affaince.subscription.product.registration.command.event;

import java.util.Map;

/**
 * Created by rbsavaliya on 10-01-2016.
 */
public class SubscribedProductCountUpdatedEvent {
    private Map<String, Integer> subscribedProductUpdateCount;

    public SubscribedProductCountUpdatedEvent(Map<String, Integer> subscribedProductUpdateCount) {
        this.subscribedProductUpdateCount = subscribedProductUpdateCount;
    }

    public SubscribedProductCountUpdatedEvent() {
    }

    public Map<String, Integer> getSubscribedProductUpdateCount() {
        return subscribedProductUpdateCount;
    }

    public void setSubscribedProductUpdateCount(Map<String, Integer> subscribedProductUpdateCount) {
        this.subscribedProductUpdateCount = subscribedProductUpdateCount;
    }
}
