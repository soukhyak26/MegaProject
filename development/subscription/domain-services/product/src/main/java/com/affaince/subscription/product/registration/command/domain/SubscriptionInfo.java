package com.affaince.subscription.product.registration.command.domain;

import java.util.List;
import java.util.Map;

/**
 * Created by rsavaliya on 17/1/16.
 */
public class SubscriptionInfo {
    private String subscriptionId;
    private String subscriberId;
    private Map<Integer,DeliveryInfo> deliveriesPerWeek;

    public SubscriptionInfo(String subscriptionId, String subscriberId, List<BasketItemInfo> basketItems) {
        this.subscriptionId = subscriptionId;
        this.subscriberId = subscriberId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public Map<Integer, DeliveryInfo> getDeliveriesPerWeek() {
        return deliveriesPerWeek;
    }

    public void setDeliveriesPerWeek(Map<Integer, DeliveryInfo> deliveriesPerWeek) {
        this.deliveriesPerWeek = deliveriesPerWeek;
    }
}
