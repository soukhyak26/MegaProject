package com.affaince.subscription.business.domain;

import com.affaince.subscription.business.event.InterestsGainCreditedEvent;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 9/5/16.
 */
public class InterestsAccount extends AbstractAnnotatedEntity {
    private double currentAmount;
    private LocalDate startDate;
    private LocalDate endDate;

    public InterestsAccount(LocalDate startDate, LocalDate endDate) {
        this.endDate = endDate;
        this.startDate = startDate;
    }

    public void credit(double amount) {
        this.currentAmount += amount;
    }


    public double getCurrentAmount() {
        return currentAmount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @EventSourcingHandler
    private void on(InterestsGainCreditedEvent event) {
        credit(event.getAmountToCredit());
    }

    public void fireCreditedEvent(Integer businessAccountId, double amountToCredit) {
        apply(new InterestsGainCreditedEvent(businessAccountId, amountToCredit));
    }
}
