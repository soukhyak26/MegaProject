package com.affaince.subscription.subscriber.command.event;

import org.joda.time.LocalDate;

/**
 * Created by rbsavaliya on 08-11-2015.
 */
public class PaymentReceivedFromSourceEvent {

    private String subscriberId;
    private double paymentAmount;
    private LocalDate paymentDate;

    public PaymentReceivedFromSourceEvent(String subscriberId, double paymentAmount, LocalDate paymentDate) {
        this.subscriberId = subscriberId;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
    }

    public PaymentReceivedFromSourceEvent() {
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
}
