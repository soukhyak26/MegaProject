package com.verifier.domains.business.view;

import org.joda.time.LocalDate;

public class VariableExpensesProvisionCalendarView extends ProvisionCalendarView {
    public VariableExpensesProvisionCalendarView(String businessAccountId, LocalDate startDate, LocalDate endDate) {
        super(businessAccountId, startDate, endDate);
    }

    public VariableExpensesProvisionCalendarView(){
        super();
    }

} 
