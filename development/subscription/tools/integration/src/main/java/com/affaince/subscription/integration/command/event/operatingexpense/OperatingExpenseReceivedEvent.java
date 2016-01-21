package com.affaince.subscription.integration.command.event.operatingexpense;

import com.affaince.subscription.common.type.ExpenseType;
import com.affaince.subscription.common.type.Period;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

/**
 * Created by rsavaliya on 21/1/16.
 */
@CsvRecord(separator = ",", skipFirstLine = true)
public class OperatingExpenseReceivedEvent {
    @DataField(name = "EXPENSE_TYPE", pos = 1, trim = true)
    private ExpenseType expenseType;
    @DataField(name = "EXPENSE_NAME", pos = 2, trim = true)
    private String expenseHeader;
    @DataField(name = "EXPENSE_AMOUNT", pos = 3, trim = true)
    private double expenseAmount;
    @DataField(name = "EXPENSE_PERIOD", pos = 4, trim = true)
    private Period period;

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
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

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "OperatingExpenseReceivedEvent{" +
                "expenseType=" + expenseType +
                ", expenseHeader='" + expenseHeader + '\'' +
                ", expenseAmount=" + expenseAmount +
                ", period=" + period +
                '}';
    }
}
