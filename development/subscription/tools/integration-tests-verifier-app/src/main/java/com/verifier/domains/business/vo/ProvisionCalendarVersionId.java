package com.verifier.domains.business.vo;

import org.joda.time.LocalDate;

import java.io.Serializable;

public class ProvisionCalendarVersionId implements Serializable {
    private String businessAccountId;
    private LocalDate startDate;
    private LocalDate endDate;

    public ProvisionCalendarVersionId(String businessAccountId, LocalDate startDate, LocalDate endDate) {
        this.businessAccountId = businessAccountId;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public ProvisionCalendarVersionId(){

    }

    public String getBusinessAccountId() {
        return businessAccountId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
