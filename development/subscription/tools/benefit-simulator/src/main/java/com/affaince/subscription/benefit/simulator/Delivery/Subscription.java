package com.affaince.subscription.benefit.simulator.Delivery;

import java.util.List;

public class Subscription {
    public List<SubscriptionItem> subscriptionItems;

    public Subscription(List<SubscriptionItem> subscriptionItems) {
        this.subscriptionItems = subscriptionItems;
    }

    public List<SubscriptionItem> getSubscriptionItems() {
        return subscriptionItems;
    }
}

