package com.verifier.domains.business.view;

import org.joda.time.LocalDate;

public class OthersProvisionCalendarView extends ProvisionCalendarView {
    public OthersProvisionCalendarView(String businessAccountId, LocalDate startDate, LocalDate endDate) {
        super(businessAccountId, startDate, endDate);
    }

    public OthersProvisionCalendarView(){
        super();
    }

} 
