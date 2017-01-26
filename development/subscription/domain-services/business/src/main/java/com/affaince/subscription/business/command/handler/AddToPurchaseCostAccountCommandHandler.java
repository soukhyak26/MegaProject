package com.affaince.subscription.business.command.handler;

import com.affaince.subscription.business.command.AddToPurchaseCostAccountCommand;
import com.affaince.subscription.business.command.domain.BusinessAccount;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 26-01-2017.
 */
@Component
public class AddToPurchaseCostAccountCommandHandler {
    private final Repository<BusinessAccount> repository;

    @Autowired
    public AddToPurchaseCostAccountCommandHandler(Repository<BusinessAccount> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(AddToPurchaseCostAccountCommand command) {
        BusinessAccount businessAccount= repository.load(command.getId());
        businessAccount.addToPurchaseCostAccount(command.getAmountTobeAdded());
    }

}
