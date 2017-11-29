package com.affaince.subscription.integration.command.event.operatingexpense;

import com.affaince.subscription.common.type.ExpenseType;
import com.affaince.subscription.common.type.Period;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import java.util.Date;

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
    @DataField(name = "EXPENSE_PERIOD_START",pattern = "yyyyddMM", pos = 5, trim = true)
    private Date startDate;
    @DataField(name = "EXPENSE_PERIOD_END",pattern = "yyyyddMM", pos = 6, trim = true)
    private Date endDate;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "OperatingExpenseReceivedEvent{" +
                "expenseType=" + expenseType +
                ", expenseId='" + expenseId + '\'' +
                ", expenseHeader='" + expenseHeader + '\'' +
                ", expenseAmount=" + expenseAmount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
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
