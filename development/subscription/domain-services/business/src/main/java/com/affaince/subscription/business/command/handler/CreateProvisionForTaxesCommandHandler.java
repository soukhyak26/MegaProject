package com.affaince.subscription.business.command.handler;

import com.affaince.subscription.business.command.CreateProvisionForNodalCommand;
import com.affaince.subscription.business.command.CreateProvisionForTaxesCommand;
import com.affaince.subscription.business.command.domain.BusinessAccount;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.AggregateNotFoundException;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 13-01-2017.
 */
@Component
public class CreateProvisionForTaxesCommandHandler {
    private final Repository<BusinessAccount> repository;

    @Autowired
    public CreateProvisionForTaxesCommandHandler(Repository<BusinessAccount> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(CreateProvisionForTaxesCommand command) {
        Integer id= command.getStartDate().getYear();
        BusinessAccount businessAccount;
        try {
            businessAccount = repository.load(id);
        } catch (AggregateNotFoundException e) {
            businessAccount = new BusinessAccount(id,command.getStartDate());
            repository.add(businessAccount);
        }
        businessAccount.registerProvisionForTaxes(command.getId(),command.getStartDate(),command.getEndDate(),command.getProvisionForOthers());
    }

}
