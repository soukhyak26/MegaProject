package com.verifier.domains.business.view;

import org.joda.time.LocalDate;

public class CommonExpenseProvisionCalendarView extends ProvisionCalendarView {
    public CommonExpenseProvisionCalendarView(String businessAccountId, LocalDate startDate, LocalDate endDate) {
        super(businessAccountId, startDate, endDate);
    }

    public CommonExpenseProvisionCalendarView(){
        super();
    }
}
