package com.affaince.subscription.business.command;

import com.affaince.subscription.common.type.SensitivityCharacteristic;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 16-01-2016.
 */
public class AddCommonOperatingExpenseCommand {
    @TargetAggregateIdentifier
    private final String id;
    private String expenseHeader;
    private double amount;
    private SensitivityCharacteristic sensitivityCharacteristic;

    public AddCommonOperatingExpenseCommand(String id, String expenseHeader, double amount, SensitivityCharacteristic sensitivityCharacteristic) {
        this.id = id;
        this.expenseHeader = expenseHeader;
        this.amount = amount;
        this.sensitivityCharacteristic = sensitivityCharacteristic;
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

    public SensitivityCharacteristic getSensitivityCharacteristic() {
        return this.sensitivityCharacteristic;
    }
}
