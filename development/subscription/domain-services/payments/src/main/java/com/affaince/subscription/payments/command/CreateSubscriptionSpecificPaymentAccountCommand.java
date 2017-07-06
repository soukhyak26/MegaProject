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
    private String paymentSchemeId;

    public CreateSubscriptionSpecificPaymentAccountCommand(String subscriberId, String subscriptionId, List<DeliveryCreatedEvent> totalSubscriptionDeliveries,String paymentSchemeId) {
        this.subscriberId = subscriberId;
        this.subscriptionId = subscriptionId;
        this.totalSubscriptionDeliveries = totalSubscriptionDeliveries;
        this.paymentSchemeId=paymentSchemeId;
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

    public String getPaymentSchemeId() {
        return paymentSchemeId;
    }
}
