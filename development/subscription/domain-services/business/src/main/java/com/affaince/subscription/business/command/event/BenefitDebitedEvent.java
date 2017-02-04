package com.affaince.subscription.business.command.event;

import org.joda.time.LocalDate;

public class BenefitDebitedEvent extends DebitedEvent {
    private String contributorId;
    private LocalDate dateOfTransaction;

    public BenefitDebitedEvent() {
    }

    public BenefitDebitedEvent(Integer businessAccountId, String contributorId,double amountToDebit,LocalDate dateOfTransaction) {
        super(businessAccountId, amountToDebit);
    }

    public String getContributorId() {
        return contributorId;
    }

    public LocalDate getDateOfTransaction() {
        return dateOfTransaction;
    }
}
