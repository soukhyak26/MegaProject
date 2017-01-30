package com.affaince.subscription.business.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 17-01-2017.
 */
public class CreateProvisionForCommonExpensesCommand {
    @TargetAggregateIdentifier
    private final Integer id;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final double provisionForCommonExpenses;

    public CreateProvisionForCommonExpensesCommand(Integer id, LocalDate startDate, LocalDate endDate, double provisionForCommonExpenses) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.provisionForCommonExpenses = provisionForCommonExpenses;
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

    public double getProvisionForCommonExpenses() {
        return provisionForCommonExpenses;
    }
}