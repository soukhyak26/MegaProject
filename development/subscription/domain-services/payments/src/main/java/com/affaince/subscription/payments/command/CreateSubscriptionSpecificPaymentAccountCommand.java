package com.affaince.subscription.payments.command;

import com.affaince.subscription.payments.command.event.DeliveryCreatedEvent;

import java.util.List;

/**
 * Created by mandar on 5/17/2017.
 */
public class CreateSubscriptionSpecificPaymentAccountCommand {
    private String subscriberId;
    private String subscriptionId;
    private List<DeliveryCreatedEvent> totalSubscriptionDeliveries;

    public CreateSubscriptionSpecificPaymentAccountCommand(String subscriberId, String subscriptionId, List<DeliveryCreatedEvent> totalSubscriptionDeliveries) {
        this.subscriberId = subscriberId;
        this.subscriptionId = subscriptionId;
        this.totalSubscriptionDeliveries = totalSubscriptionDeliveries;
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
