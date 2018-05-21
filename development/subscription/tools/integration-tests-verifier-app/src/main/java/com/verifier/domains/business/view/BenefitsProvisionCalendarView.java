package com.verifier.domains.business.view;

import org.joda.time.LocalDate;

public class BenefitsProvisionCalendarView extends ProvisionCalendarView {
    public BenefitsProvisionCalendarView(String businessAccountId, LocalDate startDate, LocalDate endDate) {
        super(businessAccountId, startDate, endDate);
    }

    public BenefitsProvisionCalendarView(){
        super();
    }

} 
