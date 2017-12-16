package com.affaince.subscription.subscriber.event;

/**
 * Created by mandar on 7/6/2017.
 */
public class PaymentSchemeSelectedEvent {
    private String subscriptionId;
    private String paymentSchemeId;
    public PaymentSchemeSelectedEvent(String subscriptionId, String paymentSchemeId) {
        this.subscriptionId=subscriptionId;
        this.paymentSchemeId=paymentSchemeId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getPaymentSchemeId() {
        return paymentSchemeId;
    }
}
