package com.affaince.subscription.business.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by mandar on 26-01-2017.
 */
public class AddToPurchaseCostAccountCommand {
    @TargetAggregateIdentifier
    private Integer Id;
    private double amountTobeAdded;

    public AddToPurchaseCostAccountCommand(Integer id, double amountTobeAdded) {
        Id = id;
        this.amountTobeAdded = amountTobeAdded;
    }

    public Integer getId() {
        return Id;
    }

    public double getAmountTobeAdded() {
        return amountTobeAdded;
    }
}
