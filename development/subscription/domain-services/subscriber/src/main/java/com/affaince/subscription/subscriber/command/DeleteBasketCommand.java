package com.affaince.subscription.subscriber.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 11-10-2015.
 */
public class DeleteBasketCommand {
    @TargetAggregateIdentifier
    private String subscriberId;
    private String basketId;

    public DeleteBasketCommand(String subscriberId, String basketId) {
        this.subscriberId = subscriberId;
        this.basketId = basketId;
    }

    public DeleteBasketCommand() {
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getBasketId() {
        return basketId;
    }
}
