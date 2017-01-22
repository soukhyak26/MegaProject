package com.affaince.subscription.business.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 17-01-2017.
 */
public class CreateProvisionForBenefitsCommand {
    @TargetAggregateIdentifier
    private final Integer id;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final double provisionForBenefits;

    public CreateProvisionForBenefitsCommand(Integer id,LocalDate startDate, LocalDate endDate, double provisionForBenefits) {
        this.id=id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.provisionForBenefits = provisionForBenefits;
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

    public double getProvisionForBenefits() {
        return provisionForBenefits;
    }
}
