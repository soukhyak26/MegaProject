package com.affaince.subscription.payments.command.event;

import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by mandar on 5/17/2017.
 */
public class SubscriptionCreatedEvent {
    private String subscriberId;
    private String subscriptionId;
    private LocalDate creationDate;
    public SubscriptionCreatedEvent(String subscriberId, String subscriptionId,LocalDate creationDate) {
        this.subscriberId=subscriberId;
        this.subscriptionId=subscriptionId;
        this.creationDate=creationDate;
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
