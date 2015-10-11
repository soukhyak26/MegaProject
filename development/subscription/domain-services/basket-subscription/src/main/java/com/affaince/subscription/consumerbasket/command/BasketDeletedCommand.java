package com.affaince.subscription.consumerbasket.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 11-10-2015.
 */
public class BasketDeletedCommand {
    @TargetAggregateIdentifier
    private String basketId;

    public BasketDeletedCommand(String basketId) {
        this.basketId = basketId;
    }

    public String getBasketId() {
        return basketId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }
}
