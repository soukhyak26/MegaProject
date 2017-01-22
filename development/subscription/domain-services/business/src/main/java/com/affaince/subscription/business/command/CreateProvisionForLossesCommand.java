package com.affaince.subscription.business.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.joda.time.LocalDate;
import org.omg.CORBA.INTERNAL;

/**
 * Created by mandar on 17-01-2017.
 */
public class CreateProvisionForLossesCommand {
    @TargetAggregateIdentifier
    private Integer id;

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final double provisionForLosses;

    public CreateProvisionForLossesCommand(Integer id,LocalDate startDate, LocalDate endDate, double provisionForLosses) {
        this.id=id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.provisionForLosses = provisionForLosses;
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getProvisionForLosses() {
        return provisionForLosses;
    }
}
