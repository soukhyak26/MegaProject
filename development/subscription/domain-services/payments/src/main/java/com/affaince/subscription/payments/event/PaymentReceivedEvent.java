package com.affaince.subscription.payments.event;

import com.affaince.subscription.date.SysDate;
import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 21/8/16.
 */
public class PaymentReceivedEvent {
    private String susbcriberId;
    private String subscriptionId;
    private double paidAmount;
    private LocalDate paymentDate;

    public PaymentReceivedEvent() {
    }

    public PaymentReceivedEvent(String susbcriberId, String subscriptionId, double paidAmount, LocalDate paymentDate) {
        this.susbcriberId = susbcriberId;
        this.subscriptionId = subscriptionId;
        this.paidAmount = paidAmount;
        this.paymentDate = paymentDate;
    }

    public String getSusbcriberId() {
        return susbcriberId;
    }

    public void setSusbcriberId(String susbcriberId) {
        this.susbcriberId = susbcriberId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
}
