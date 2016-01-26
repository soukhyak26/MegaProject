package com.affaince.subscription.business.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 16-01-2016.
 */
public class AddCommonOperatingExpenseCommand {
    @TargetAggregateIdentifier
    private final String id;
    private String expenseHeader;
    private double amount;

    public AddCommonOperatingExpenseCommand(String id, String expenseHeader, double amount) {
        this.id = id;
        this.expenseHeader = expenseHeader;
        this.amount = amount;
    }

    public String getId() {
        return this.id;
    }

    public String getExpenseHeader() {
        return this.expenseHeader;
    }

    public double getAmount() {
        return this.amount;
    }
}
