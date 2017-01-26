package com.affaince.subscription.integration.command.event.operatingexpense;

import com.affaince.subscription.common.type.ExpenseType;
import com.affaince.subscription.common.type.Period;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ",", skipFirstLine = true)
public class OperatingExpenseReceivedEvent {
    @DataField(name = "EXPENSE_TYPE", pos = 1, trim = true)
    private ExpenseType expenseType;
    @DataField(name = "EXPENSE_TYPE_ID", pos = 2, trim = true)
    private String expenseId;
    @DataField(name = "EXPENSE_NAME", pos = 3, trim = true)
    private String expenseHeader;
    @DataField(name = "EXPENSE_AMOUNT", pos = 4, trim = true)
    private double expenseAmount;
    @DataField(name = "EXPENSE_PERIOD", pos = 5, trim = true)
    private Period period;
    @DataField(name = "FOR_MONTH", pos = 6, trim = true)
    private int forMonth;
    @DataField(name = "FOR_YEAR", pos = 7, trim = true)
    private int forYear;


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

    public int getForMonth() {
        return this.forMonth;
    }

    public void setForMonth(int forMonth) {
        this.forMonth = forMonth;
    }

    public int getForYear() {
        return this.forYear;
    }

    public void setForYear(int forYear) {
        this.forYear = forYear;
    }

    @Override
    public String toString() {
        return "OperatingExpenseReceivedEvent{" +
                "expenseType=" + expenseType +
                ", expenseHeader='" + expenseHeader + '\'' +
                ", expenseAmount=" + expenseAmount +
                ", period=" + period +
                ", forMonth=" + forMonth +
                ", forYear=" + forYear +
                '}';
    }

    public String getExpenseId() {
        if(expenseType == ExpenseType.COMMON_EXPENSE) {
            return "COMMON_EXPENSE_ID";
        }else{
            return "SUBSCRIPTION_SPECIFIC_EXPENSE_ID";
        }
    }
}
