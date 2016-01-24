package com.affaince.subscription.product.registration.command.handler;

import com.affaince.subscription.product.registration.command.RemovePastCommonExpenseTypesCommand;
import com.affaince.subscription.product.registration.command.domain.CommonOperatingExpense;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mandark on 24-01-2016.
 */
public class RemovePastCommonExpenseTypesCommandHandler {
    private final Repository<CommonOperatingExpense> repository;

    @Autowired

    public RemovePastCommonExpenseTypesCommandHandler(Repository<CommonOperatingExpense> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(RemovePastCommonExpenseTypesCommand command) {
        CommonOperatingExpense expense = repository.load(command.getCommonOperatingExpenseId());
        expense.removePastCommonExpenses(command.getCommonOperatingExpenseId(), command.getExpenseHeader(), command.getMonthOfYear());
    }
}