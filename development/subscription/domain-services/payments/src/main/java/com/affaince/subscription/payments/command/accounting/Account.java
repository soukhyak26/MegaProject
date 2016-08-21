package com.affaince.subscription.payments.command.accounting;

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 21/8/16.
 */
public abstract class Account extends AbstractAnnotatedEntity {
    private double amount;
    private LocalDate transactionDate;
}
