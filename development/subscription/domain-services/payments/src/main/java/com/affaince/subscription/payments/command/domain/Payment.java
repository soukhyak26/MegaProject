package com.affaince.subscription.payments.command.domain;

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by anayonkar on 21/8/16.
 */
public class Payment extends AbstractAnnotatedAggregateRoot<String> {

    @AggregateIdentifier
    private String paymentId;

    public Payment() {
    }

    public Payment(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

}
