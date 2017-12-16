package com.affaince.subscription.payments.event;

/**
 * Created by mandar on 7/9/2017.
 */
public class PaymentSchemeSetForPaymentEvent {
    private String subscriptionId;
    private String paymentSchemeId;
    public PaymentSchemeSetForPaymentEvent(String subscriptionId, String paymentSchemeId) {
        this.subscriptionId=subscriptionId;
        this.paymentSchemeId=paymentSchemeId;
    }

    public PaymentSchemeSetForPaymentEvent() {
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getPaymentSchemeId() {
        return paymentSchemeId;
    }
}