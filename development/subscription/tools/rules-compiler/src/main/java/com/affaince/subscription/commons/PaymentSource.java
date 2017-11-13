package com.affaince.subscription.commons;

/**
 * Created by rahul on 15/7/17.
 */
public enum PaymentSource {
    CURRENT_SUBSCRIPTION_AMOUNT ("current subscription amount"), DELIVERY ("delivery");


    private String paymentSourceValue;

    PaymentSource(String paymentSourceValue) {

        this.paymentSourceValue = paymentSourceValue;
    }

    public String getPaymentSourceValue() {
        return paymentSourceValue;
    }
}
