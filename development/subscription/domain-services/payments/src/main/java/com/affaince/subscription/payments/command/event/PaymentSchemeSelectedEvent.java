package com.affaince.subscription.payments.command.event;

/**
 * Created by mandar on 7/9/2017.
 */
public class PaymentSchemeSelectedEvent {
    private String subscriptionId;
    private String paymentSchemeId;
    public PaymentSchemeSelectedEvent(String subscriptionId, String paymentSchemeId) {
        this.subscriptionId=subscriptionId;
        this.paymentSchemeId=paymentSchemeId;
    }

    public PaymentSchemeSelectedEvent() {
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getPaymentSchemeId() {
        return paymentSchemeId;
    }

}
