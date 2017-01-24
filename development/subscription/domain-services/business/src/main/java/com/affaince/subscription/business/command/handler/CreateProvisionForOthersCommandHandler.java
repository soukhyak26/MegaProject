package com.affaince.subscription.business.command.handler;

import com.affaince.subscription.business.command.CreateProvisionForBenefitsCommand;
import com.affaince.subscription.business.command.CreateProvisionForOthersCommand;
import com.affaince.subscription.business.command.domain.BusinessAccount;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 13-01-2017.
 */
@Component
public class CreateProvisionForOthersCommandHandler {
    private final Repository<BusinessAccount> repository;

    @Autowired
    public CreateProvisionForOthersCommandHandler(Repository<BusinessAccount> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(CreateProvisionForOthersCommand command) {
        Integer id= command.getId();
        BusinessAccount businessAccount= repository.load(id);
        if(null== businessAccount) {
            businessAccount = new BusinessAccount(id,command.getStartDate());
            repository.add(businessAccount);
        }
        businessAccount.registerProvisionForOtherCost(command.getId(),command.getStartDate(),command.getEndDate(),command.getProvisionForOthers());
    }

}
