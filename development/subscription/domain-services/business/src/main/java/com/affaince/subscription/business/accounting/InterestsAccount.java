package com.affaince.subscription.business.accounting;

import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 9/5/16.
 */
public class InterestsAccount extends Account {
    public InterestsAccount(double startAmount, LocalDate endDate) {
        super(AccountType.INTERESTS, startAmount, endDate);
    }

    @Override
    public void fireCreditedEvent(String businessAccountId, double amountToCredit) {

    }

    @Override
    public void fireDebitedEvent(String businessAccountId, double amountToDebit) {

    }
}