package com.affaince.subscription.business.event;

import com.affaince.subscription.common.type.SensitivityCharacteristic;
import org.joda.time.YearMonth;

/**
 * Created by mandark on 24-01-2016.
 */
public class CommonExpenseCreatedEvent {
    private Integer commonOperatingExpenseId;
    private YearMonth monthOfYear;
    private String expenseHeader;
    private double amount;
    private SensitivityCharacteristic sensitivityCharacteristic;
    public CommonExpenseCreatedEvent() {
    }

    public CommonExpenseCreatedEvent(Integer id, YearMonth monthOfYear, String expenseHeader, double amount, SensitivityCharacteristic sensitivityCharacteristic) {
        this.commonOperatingExpenseId = id;
        this.monthOfYear=monthOfYear;
        this.expenseHeader = expenseHeader;
        this.amount = amount;
        this.sensitivityCharacteristic = sensitivityCharacteristic;
    }

    public String getExpenseHeader() {
        return this.expenseHeader;
    }

    public double getAmount() {
        return this.amount;
    }

    public Integer getCommonOperatingExpenseId() {
        return this.commonOperatingExpenseId;
    }

    public SensitivityCharacteristic getSensitivityCharacteristic() {
        return this.sensitivityCharacteristic;
    }

    public YearMonth getMonthOfYear() {
        return monthOfYear;
    }
}
