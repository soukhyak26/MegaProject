package com.affaince.subscription.product.registration.command;

import org.joda.time.YearMonth;

/**
 * Created by mandark on 24-01-2016.
 */
public class RemovePastCommonExpenseTypesCommand {
    private String commonOperatingExpenseId;
    private String expenseHeader;
    private YearMonth monthOfYear;

    public RemovePastCommonExpenseTypesCommand(String commonOperatingExpenseId, String expenseHeader, YearMonth monthOfYear) {
        this.commonOperatingExpenseId = commonOperatingExpenseId;
        this.expenseHeader = expenseHeader;
        this.monthOfYear = monthOfYear;
    }

    public String getCommonOperatingExpenseId() {
        return this.commonOperatingExpenseId;
    }

    public void setCommonOperatingExpenseId(String commonOperatingExpenseId) {
        this.commonOperatingExpenseId = commonOperatingExpenseId;
    }

    public String getExpenseHeader() {
        return this.expenseHeader;
    }

    public void setExpenseHeader(String expenseHeader) {
        this.expenseHeader = expenseHeader;
    }

    public YearMonth getMonthOfYear() {
        return this.monthOfYear;
    }

    public void setMonthOfYear(YearMonth monthOfYear) {
        this.monthOfYear = monthOfYear;
    }
}
