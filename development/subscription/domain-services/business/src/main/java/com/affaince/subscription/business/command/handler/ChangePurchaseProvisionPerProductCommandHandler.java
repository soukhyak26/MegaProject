package com.affaince.subscription.business.command.handler;

import com.affaince.subscription.business.command.ChangePurchaseProvisionPerProductCommand;
import com.affaince.subscription.business.command.domain.BusinessAccount;
import com.affaince.subscription.date.SysDate;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChangePurchaseProvisionPerProductCommandHandler {
    private final Repository<BusinessAccount> repository;

    @Autowired
    public ChangePurchaseProvisionPerProductCommandHandler(Repository<BusinessAccount> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(ChangePurchaseProvisionPerProductCommand command) {
        BusinessAccount businessAccount = repository.load(command.getId());
        //businessAccount.adjustPurchaseCost(command.getTotalPurchaseCost());
        //TODO: what to do??
        businessAccount.addToPurchaseCostAccount(command.getProvisionAdjustment());
    }
}
