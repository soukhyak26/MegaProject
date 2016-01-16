package com.affaince.notification.events;

import org.joda.time.LocalDate;

/**
 * Created by rbsavaliya on 16-01-2016.
 */
public class PaymentProcessedEvent {
    private String subscriptionId;
    private String subscriberId;
    private double paymentAmount;
    private LocalDate paymentDate;

    public PaymentProcessedEvent(String subscriptionId, String subscriberId, double paymentAmount, LocalDate paymentDate) {
        this.subscriptionId = subscriptionId;
        this.subscriberId = subscriberId;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
    }

    public PaymentProcessedEvent() {
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
}
