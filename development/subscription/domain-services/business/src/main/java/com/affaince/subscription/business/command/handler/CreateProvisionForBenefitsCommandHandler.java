package com.affaince.subscription.business.command.handler;

import com.affaince.subscription.business.command.CreateProvisionForBenefitsCommand;
import com.affaince.subscription.business.command.CreateProvisionForPurchaseCostCommand;
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
public class CreateProvisionForBenefitsCommandHandler {
    private final Repository<BusinessAccount> repository;

    @Autowired
    public CreateProvisionForBenefitsCommandHandler(Repository<BusinessAccount> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(CreateProvisionForBenefitsCommand command) {
        Integer id= command.getStartDate().getYear();
        BusinessAccount businessAccount;
        try {
            businessAccount = repository.load(id);
        } catch (AggregateNotFoundException e) {
            businessAccount = new BusinessAccount(id,command.getStartDate());
            repository.add(businessAccount);
        }
        businessAccount.registerProvisionForBenefits(command.getId(),command.getStartDate(),command.getEndDate(),command.getProvisionForBenefits());
    }
}
