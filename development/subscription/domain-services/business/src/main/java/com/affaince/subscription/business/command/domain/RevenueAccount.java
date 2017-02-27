package com.affaince.subscription.business.command.domain;

import com.affaince.subscription.business.command.event.CreditedEvent;
import com.affaince.subscription.business.command.event.DebitedEvent;
import com.affaince.subscription.business.command.event.RevenueCreditedEvent;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 9/5/16.
 */
public class RevenueAccount extends AbstractAnnotatedEntity {
    private double currentAmount;
    private LocalDate startDate;
    private LocalDate endDate;

    public RevenueAccount(LocalDate startDate, LocalDate endDate) {
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


    public void creditToRevenue(Integer businessAccountId, String contributorId,double amountToCredit) {
        apply(new RevenueCreditedEvent(businessAccountId, contributorId,amountToCredit));
    }

    @EventSourcingHandler
    public void on(RevenueCreditedEvent event) {
        credit(event.getAmountToCredit());
    }
}
