package com.affaince.subscription.payments.command.event;

import java.util.List;

/**
 * Created by mandar on 5/17/2017.
 */
public class SubscriptionSpecificDeliveriesCreatedAggregateEvent {
    private String subscriberId;
    private String subscriptionId;
    private List<DeliveryCreatedEvent> totalSubscriptionDeliveries;
    public SubscriptionSpecificDeliveriesCreatedAggregateEvent(String subscriberId, String subscriptionId, List<DeliveryCreatedEvent> totalSubscriptionDeliveries) {
        this.subscriberId=subscriberId;
        this.subscriptionId=subscriptionId;
        this.totalSubscriptionDeliveries=totalSubscriptionDeliveries;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public List<DeliveryCreatedEvent> getTotalSubscriptionDeliveries() {
        return totalSubscriptionDeliveries;
    }

}
