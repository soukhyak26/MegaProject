package com.affaince.subscription.consumerbasket.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 08-11-2015.
 */
public class SubscriptionActivationCommand {
    @TargetAggregateIdentifier
    private String basketId;

    public SubscriptionActivationCommand(String basketId) {
        this.basketId = basketId;
    }

    public String getBasketId() {
        return basketId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }
}
