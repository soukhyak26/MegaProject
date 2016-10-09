package com.affaince.subscription.subscriber.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 10-09-2015.
 */
public class DeleteItemFromSubscriptionCommand {
    @TargetAggregateIdentifier
    private String subscriberId;
    private String itemId;

    public DeleteItemFromSubscriptionCommand(String subscriberId, String itemId) {
        this.subscriberId = subscriberId;
        this.itemId = itemId;
    }

    public DeleteItemFromSubscriptionCommand() {
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getItemId() {
        return itemId;
    }
}
