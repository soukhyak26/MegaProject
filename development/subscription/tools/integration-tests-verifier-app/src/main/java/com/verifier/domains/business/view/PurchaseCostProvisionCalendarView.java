package com.verifier.domains.business.view;

import org.joda.time.LocalDate;

public class PurchaseCostProvisionCalendarView extends ProvisionCalendarView {
    public PurchaseCostProvisionCalendarView(String businessAccountId, LocalDate startDate, LocalDate endDate) {
        super(businessAccountId, startDate, endDate);
    }

    public PurchaseCostProvisionCalendarView(){
        super();
    }

}
