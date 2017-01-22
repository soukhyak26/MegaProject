package com.affaince.subscription.business.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 17-01-2017.
 */
public class CreateProvisionForOthersCommand {
    @TargetAggregateIdentifier
    private Integer id;

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final double provisionForOthers;

    public CreateProvisionForOthersCommand(Integer id,LocalDate startDate, LocalDate endDate, double provisionForOthers) {
        this.id=id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.provisionForOthers = provisionForOthers;
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

    public double getProvisionForOthers() {
        return provisionForOthers;
    }
}
