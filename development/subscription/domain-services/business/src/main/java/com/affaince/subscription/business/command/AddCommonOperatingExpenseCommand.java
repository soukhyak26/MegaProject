package com.affaince.subscription.business.command;

import com.affaince.subscription.common.type.SensitivityCharacteristic;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.joda.time.YearMonth;

/**
 * Created by rbsavaliya on 16-01-2016.
 */
public class AddCommonOperatingExpenseCommand {
    @TargetAggregateIdentifier
    private final String id;
    private final YearMonth monthOfYear;
    private String expenseHeader;
    private double amount;
    private SensitivityCharacteristic sensitivityCharacteristic;

    public AddCommonOperatingExpenseCommand(String id, YearMonth monthOfYear,String expenseHeader, double amount, SensitivityCharacteristic sensitivityCharacteristic) {
        this.id = id;
        this.monthOfYear=monthOfYear;
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

    public YearMonth getMonthOfYear() {
        return monthOfYear;
    }
}
