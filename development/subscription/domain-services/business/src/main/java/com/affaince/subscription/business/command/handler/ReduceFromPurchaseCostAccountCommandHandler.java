package com.affaince.subscription.business.command.handler;

import com.affaince.subscription.business.command.AddToPurchaseCostAccountCommand;
import com.affaince.subscription.business.command.ReduceFromPurchaseCostAccountCommand;
import com.affaince.subscription.business.command.domain.BusinessAccount;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.AggregateNotFoundException;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 26-01-2017.
 */
@Component
public class ReduceFromPurchaseCostAccountCommandHandler {
    private final Repository<BusinessAccount> repository;

    @Autowired
    public ReduceFromPurchaseCostAccountCommandHandler(Repository<BusinessAccount> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(ReduceFromPurchaseCostAccountCommand command) {
        BusinessAccount businessAccount=null;
        try {
            businessAccount = repository.load(command.getId());
        }catch(AggregateNotFoundException ex){
            businessAccount=new BusinessAccount(command.getId(),command.getProvisionsDate());
        }
        businessAccount.reduceFromPurchaseCostAccount(command.getId(),command.getProductId(),command.getGetTotalProductSubscriptionCount(),command.getCurrentProductPurchasePrice());
    }

}
