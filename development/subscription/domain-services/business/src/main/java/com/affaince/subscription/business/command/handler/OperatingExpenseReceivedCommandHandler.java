package com.affaince.subscription.business.command.handler;

import com.affaince.subscription.business.command.OperatingExpenseReceivedCommand;
import com.affaince.subscription.business.command.ProductStatusReceivedCommand;
import com.affaince.subscription.business.command.domain.BusinessAccount;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 8/5/16.
 */
@Component
public class OperatingExpenseReceivedCommandHandler {
    private final Repository<BusinessAccount> repository;

    @Autowired
    public OperatingExpenseReceivedCommandHandler(Repository<BusinessAccount> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(OperatingExpenseReceivedCommand command) {
        BusinessAccount businessAccount = repository.load(command.getBusinessAccountId());
        businessAccount.adjustOperatingExpenses(command.getExpenseType(), command.getExpenseAmount());
    }
}