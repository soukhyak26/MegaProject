package com.affaince.subscription.payments.simulator.request;

public class PaymentSimulatorRequest {

    private PaymentScheme paymentScheme;
    private Subscription subscription;

    public PaymentScheme getPaymentScheme() {
        return paymentScheme;
    }

    public void setPaymentScheme(PaymentScheme paymentScheme) {
        this.paymentScheme = paymentScheme;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public String toString() {
        return "PaymentSimulatorRequest{" +
                "paymentScheme=" + paymentScheme +
                ", subscription=" + subscription +
                '}';
    }
}
