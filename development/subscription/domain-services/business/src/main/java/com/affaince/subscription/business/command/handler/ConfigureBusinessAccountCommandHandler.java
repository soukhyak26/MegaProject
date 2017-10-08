package com.affaince.subscription.business.command.handler;

import com.affaince.subscription.business.command.ConfigureBusinessAccountCommand;
import com.affaince.subscription.business.command.domain.BusinessAccount;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 10/8/2017.
 */
@Component
public class ConfigureBusinessAccountCommandHandler {
    private final Repository<BusinessAccount> repository;

    @Autowired
    public ConfigureBusinessAccountCommandHandler(Repository<BusinessAccount> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(ConfigureBusinessAccountCommand command){
        BusinessAccount businessAccount = repository.load(command.getId());
        businessAccount.configureBusinessAccount(command.getBudgetAdjustmentOptions());
    }
}
