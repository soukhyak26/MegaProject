package com.affaince.subscription.business.command.handler;

import com.affaince.subscription.business.command.UpdateFixedExpenseToProductCommand;
import com.affaince.subscription.business.command.domain.BusinessAccount;
import com.affaince.subscription.date.SysDate;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 10-01-2017.
 */
@Component
public class UpdateFixedExpenseToProductCommandHandler {
    private final Repository<BusinessAccount> repository;

    @Autowired
    public UpdateFixedExpenseToProductCommandHandler(Repository<BusinessAccount> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(UpdateFixedExpenseToProductCommand command){
        BusinessAccount businessAccount = repository.load(Integer.valueOf(SysDate.now().getYear()).toString());
        businessAccount.updateFixedExpenseToProduct(command.getProductId(),command.getDistributionAmountPerUnit(),command.getDistributionDate());
    }
}
