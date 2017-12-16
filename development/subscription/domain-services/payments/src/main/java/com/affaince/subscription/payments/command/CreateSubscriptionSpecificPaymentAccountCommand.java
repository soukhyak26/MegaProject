package com.affaince.subscription.payments.command;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 5/17/2017.
 */
public class CreateSubscriptionSpecificPaymentAccountCommand {
    private String subscriberId;
    private String subscriptionId;
    private LocalDate creationDate;
    public CreateSubscriptionSpecificPaymentAccountCommand(String subscriberId, String subscriptionId,LocalDate creationDate) {
        this.subscriberId = subscriberId;
        this.subscriptionId = subscriptionId;
        this.creationDate = creationDate;
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
