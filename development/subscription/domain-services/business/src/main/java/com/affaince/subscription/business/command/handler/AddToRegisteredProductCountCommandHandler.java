package com.affaince.subscription.business.command.handler;

import com.affaince.subscription.business.command.AddToRegisteredProductCountCommand;
import com.affaince.subscription.business.command.domain.BusinessAccount;
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

    public void handle(AddToRegisteredProductCountCommand command){
        BusinessAccount businessAccount= repository.load(command.getBusinessAccountId());
        businessAccount.addRegisteredProductCount(command.getRegisteredProductCount());
    }
}
