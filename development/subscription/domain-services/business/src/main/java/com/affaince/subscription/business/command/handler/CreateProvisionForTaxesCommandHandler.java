package com.affaince.subscription.business.command.handler;

import com.affaince.subscription.business.command.CreateProvisionForNodalCommand;
import com.affaince.subscription.business.command.CreateProvisionForTaxesCommand;
import com.affaince.subscription.business.command.domain.BusinessAccount;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mandar on 13-01-2017.
 */
public class CreateProvisionForTaxesCommandHandler {
    private final Repository<BusinessAccount> repository;

    @Autowired
    public CreateProvisionForTaxesCommandHandler(Repository<BusinessAccount> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(CreateProvisionForTaxesCommand command) {
        Integer id= command.getStartDate().getYear();
        BusinessAccount businessAccount= repository.load(id);
        if(null== businessAccount) {
            businessAccount = new BusinessAccount(id,command.getStartDate());
            repository.add(businessAccount);
        }
        businessAccount.registerProvisionForTaxes(command.getId(),command.getStartDate(),command.getEndDate(),command.getProvisionForOthers());
    }

}
