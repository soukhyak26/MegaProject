package com.affaince.subscription.business.command.handler;

import com.affaince.subscription.business.command.CreateProvisionCommand;
import com.affaince.subscription.business.command.domain.BusinessAccount;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by anayonkar on 29/4/16.
 */
@Component
public class CreateProvisionCommandHandler {
    private final Repository<BusinessAccount> repository;

    @Autowired
    public CreateProvisionCommandHandler(Repository<BusinessAccount> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(CreateProvisionCommand command) {
        /*BusinessAccount businessAccount =  null;//repository.load(command.getBusinessAccountId());
        if(businessAccount == null) {
            //TODO : copy last year's business account's assets and liabilities
            businessAccount = new BusinessAccount(command.getBusinessAccountId());
            repository.add(businessAccount);
        }
        LocalDate provisionDate = command.getProvisionDate();
        double provisionForPurchaseCost = command.getProvisionForPurchaseCost();
        businessAccount.createProvisionForPurchaseCost(command.getBusinessAccountId(), command.getProvisionForPurchaseCost(), command.getProvisionDate());*/
        BusinessAccount businessAccount = new BusinessAccount(command.getBusinessAccountId(),
                command.getProvisionList(),
                command.getProvisionDate());
        repository.add(businessAccount);
    }

}
