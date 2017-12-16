package com.affaince.subscription.business.event;


import org.joda.time.LocalDate;

public class BookingAmountDebitedEvent extends DebitedEvent {
    private String contributorId;
    private LocalDate dateOfTransaction;
    public BookingAmountDebitedEvent() {
    }

    public BookingAmountDebitedEvent(Integer businessAccountId, String contributorId,double amountToDebit,LocalDate dateOfTransaction) {
        super(businessAccountId, amountToDebit);
        this.contributorId=contributorId;
        this.dateOfTransaction=dateOfTransaction;
    }

    public String getContributorId() {
        return contributorId;
    }

    public LocalDate getDateOfTransaction() {
        return dateOfTransaction;
    }
}
