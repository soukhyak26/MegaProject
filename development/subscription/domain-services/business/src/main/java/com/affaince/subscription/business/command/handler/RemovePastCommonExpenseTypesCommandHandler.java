package com.affaince.subscription.business.command.handler;

import com.affaince.subscription.business.command.RemovePastCommonExpenseTypesCommand;
import com.affaince.subscription.business.command.domain.MonthlyCommonOperatingExpense;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mandark on 24-01-2016.
 */
public class RemovePastCommonExpenseTypesCommandHandler {
    private final Repository<MonthlyCommonOperatingExpense> repository;

    @Autowired

    public RemovePastCommonExpenseTypesCommandHandler(Repository<MonthlyCommonOperatingExpense> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(RemovePastCommonExpenseTypesCommand command) {
        MonthlyCommonOperatingExpense expense = repository.load(command.getCommonOperatingExpenseId());
        expense.removePastCommonExpenses(command.getCommonOperatingExpenseId(), command.getExpenseHeader(), command.getMonthOfYear());
    }
}