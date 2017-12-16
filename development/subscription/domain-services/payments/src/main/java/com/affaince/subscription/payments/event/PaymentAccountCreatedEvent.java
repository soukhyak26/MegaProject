package com.affaince.subscription.payments.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 5/17/2017.
 */
public class PaymentAccountCreatedEvent {
    private String subscriberId;
    private String subscriptionId;
    private LocalDate creationDate;
    public PaymentAccountCreatedEvent(String subscriberId,String subscriptionId,LocalDate creationDate) {
        this.subscriberId=subscriberId;
        this.subscriptionId=subscriptionId;
        this.creationDate=creationDate;
    }

    public PaymentAccountCreatedEvent() {
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }
}
