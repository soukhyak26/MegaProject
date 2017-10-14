package com.affaince.subscription.business.command;

import com.affaince.subscription.business.vo.OperatingExpenseVO;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by mandar on 17-01-2017.
 */
public class CreateProvisionForCommonExpensesCommand {
    @TargetAggregateIdentifier
    private final Integer id;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final List<OperatingExpenseVO> expenses;

    public CreateProvisionForCommonExpensesCommand(Integer id, LocalDate startDate, LocalDate endDate,List<OperatingExpenseVO> expenses) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.expenses=expenses;
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


    public List<OperatingExpenseVO> getExpenses() {
        return expenses;
    }
}
