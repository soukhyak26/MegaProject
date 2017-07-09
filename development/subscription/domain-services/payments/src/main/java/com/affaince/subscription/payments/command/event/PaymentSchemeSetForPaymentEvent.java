package com.affaince.subscription.payments.command.event;

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

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getPaymentSchemeId() {
        return paymentSchemeId;
    }
}
