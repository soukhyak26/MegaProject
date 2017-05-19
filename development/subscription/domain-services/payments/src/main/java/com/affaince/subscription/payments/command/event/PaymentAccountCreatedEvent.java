package com.affaince.subscription.payments.command.event;

/**
 * Created by mandar on 5/17/2017.
 */
public class PaymentAccountCreatedEvent {
    private String subscriberId;
    private String subscriptionId;
    public PaymentAccountCreatedEvent(String subscriberId,String subscriptionId) {
        this.subscriberId=subscriberId;
        this.subscriptionId=subscriptionId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }
}
