package com.affaince.subscription.subscriber.query.event;

/**
 * Created by rbsavaliya on 08-11-2015.
 */
public class PaymentConfirmedEvent {

    private String subscriptionId;
    private double paymentAmount;

    public PaymentConfirmedEvent(String subscriptionId, double paymentAmount) {
        this.subscriptionId = subscriptionId;
        this.paymentAmount = paymentAmount;
    }

    public PaymentConfirmedEvent() {
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
