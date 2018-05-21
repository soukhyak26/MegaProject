package com.verifier.domains.business.view;

import org.joda.time.LocalDate;

public class TaxesProvisionCalendarView extends ProvisionCalendarView {
    public TaxesProvisionCalendarView(String businessAccountId, LocalDate startDate, LocalDate endDate) {
        super(businessAccountId, startDate, endDate);
    }

    public TaxesProvisionCalendarView(){
        super();
    }

}
