package com.affaince.subscription.business.command.handler;

import com.affaince.subscription.business.command.AddToRegisteredProductCountCommand;
import com.affaince.subscription.business.command.domain.BusinessAccount;
import com.affaince.subscription.date.SysDate;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.AggregateNotFoundException;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 4/16/2017.
 */
@Component
public class AddToRegisteredProductCountCommandHandler {
    private final Repository<BusinessAccount> repository;

    @Autowired
    public AddToRegisteredProductCountCommandHandler(Repository<BusinessAccount> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(AddToRegisteredProductCountCommand command){
        BusinessAccount businessAccount;
        try {
            businessAccount = repository.load(command.getBusinessAccountId());
        } catch (AggregateNotFoundException e) {
            businessAccount = new BusinessAccount(command.getBusinessAccountId(), SysDate.now());
            repository.add(businessAccount);
        }
        businessAccount.addRegisteredProductCount(command.getRegisteredProductCount());
    }
}
