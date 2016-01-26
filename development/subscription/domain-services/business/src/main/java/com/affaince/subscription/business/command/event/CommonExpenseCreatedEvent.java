package com.affaince.subscription.business.command.event;

import com.affaince.subscription.common.type.SensitivityCharacteristic;
import org.joda.time.YearMonth;

/**
 * Created by mandark on 24-01-2016.
 */
public class CommonExpenseCreatedEvent {
    private String commonOperatingExpenseId;
    private String expenseHeader;
    private double amount;
    private SensitivityCharacteristic sensitivityCharacteristic;
    private YearMonth monthOfYear;

    public CommonExpenseCreatedEvent(String id, String expenseHeader, double amount, SensitivityCharacteristic sensitivityCharacteristic, YearMonth monthOfYear) {
        this.commonOperatingExpenseId = id;
        this.expenseHeader = expenseHeader;
        this.amount = amount;
        this.sensitivityCharacteristic = sensitivityCharacteristic;
        this.monthOfYear = monthOfYear;
    }

    public String getExpenseHeader() {
        return this.expenseHeader;
    }

    public double getAmount() {
        return this.amount;
    }

    public String getCommonOperatingExpenseId() {
        return this.commonOperatingExpenseId;
    }

    public YearMonth getMonthOfYear() {
        return this.monthOfYear;
    }

    public SensitivityCharacteristic getSensitivityCharacteristic() {
        return this.sensitivityCharacteristic;
    }
}
