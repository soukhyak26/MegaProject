package com.affaince.subscription.commons;

/**
 * Created by rahul on 15/7/17.
 */
public enum PaymentEvent {
    SUBSCRIPTION_CONFIRMATION ("subscription confirmation");

    private String paymentEventValue;

    PaymentEvent(String paymentEventValue) {

        this.paymentEventValue = paymentEventValue;
    }

    public String getPaymentEventValue() {
        return paymentEventValue;
    }
}
