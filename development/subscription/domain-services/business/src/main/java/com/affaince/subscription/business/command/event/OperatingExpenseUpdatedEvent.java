package com.affaince.subscription.business.command.event;

import com.affaince.subscription.common.type.ExpenseType;
import com.affaince.subscription.common.type.SensitivityCharacteristic;

/**
 * Created by mandark on 24-01-2016.
 */
public class OperatingExpenseUpdatedEvent {
    private String commonOperatingExpenseId;
    private String expenseHeader;
    private double expenseAmount;
    private SensitivityCharacteristic sensitivityCharacteristic;

    public OperatingExpenseUpdatedEvent(String commonOperatingExpenseId, String expenseHeader, double expenseAmount, SensitivityCharacteristic sensitivityCharacteristic) {
        this.commonOperatingExpenseId = commonOperatingExpenseId;
        this.expenseHeader = expenseHeader;
        this.expenseAmount = expenseAmount;
        this.sensitivityCharacteristic = sensitivityCharacteristic;
    }

    public String getExpenseHeader() {
        return expenseHeader;
    }

    public void setExpenseHeader(String expenseHeader) {
        this.expenseHeader = expenseHeader;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public SensitivityCharacteristic getSensitivityCharacteristic() {
        return sensitivityCharacteristic;
    }

    public void setSensitivityCharacteristic(SensitivityCharacteristic sensitivityCharacteristic) {
        this.sensitivityCharacteristic = sensitivityCharacteristic;
    }

    @Override
    public String toString() {
        return "OperatingExpenseUpdatedEvent{" +
                ", expenseHeader='" + expenseHeader + '\'' +
                ", expenseAmount=" + expenseAmount +
                '}';
    }

    public String getCommonOperatingExpenseId() {
        return this.commonOperatingExpenseId;
    }

    public void setCommonOperatingExpenseId(String commonOperatingExpenseId) {
        this.commonOperatingExpenseId = commonOperatingExpenseId;
    }
}
