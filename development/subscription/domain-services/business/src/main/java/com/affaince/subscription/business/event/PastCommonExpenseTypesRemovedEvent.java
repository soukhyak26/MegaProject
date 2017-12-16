package com.affaince.subscription.business.event;

import org.joda.time.YearMonth;

/**
 * Created by mandark on 24-01-2016.
 */
public class PastCommonExpenseTypesRemovedEvent {
    private String id;
    private String expenseHeader;
    private YearMonth monthOfYear;

    public PastCommonExpenseTypesRemovedEvent() {
    }

    public PastCommonExpenseTypesRemovedEvent(String id, String expenseHeader, YearMonth yearMonth) {
        this.id = id;
        this.expenseHeader = expenseHeader;
        this.monthOfYear = yearMonth;
    }

    public String getId() {
        return this.id;
    }

    public YearMonth getMonthOfYear() {
        return this.monthOfYear;
    }

    public String getExpenseHeader() {
        return this.expenseHeader;
    }
}
