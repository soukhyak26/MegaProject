package com.affaince.subscription.payments.command.event;

/**
 * Created by mandar on 5/17/2017.
 */
public class PaymentAccountCreatedEvent {
    private String subscriberId;
    private String subscriptionId;
    private String schemeId;
    public PaymentAccountCreatedEvent(String subscriberId,String subscriptionId,String schemeId) {
        this.subscriberId=subscriberId;
        this.subscriptionId=subscriptionId;
        this.schemeId=schemeId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getSchemeId() {
        return schemeId;
    }
}
