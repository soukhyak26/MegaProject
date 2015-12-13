package com.affaince.subscription.subscriber.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 10-09-2015.
 */
public class DeleteItemFromSubscriptionCommand {
    @TargetAggregateIdentifier
    private String subscriptionId;
    private String itemId;

    public DeleteItemFromSubscriptionCommand(String subscriptionId, String itemId) {
        this.subscriptionId = subscriptionId;
        this.itemId = itemId;
    }

    public DeleteItemFromSubscriptionCommand() {
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
