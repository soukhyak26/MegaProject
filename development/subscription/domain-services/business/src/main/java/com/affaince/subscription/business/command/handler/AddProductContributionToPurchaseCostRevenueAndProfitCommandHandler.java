package com.affaince.subscription.business.command.handler;

import com.affaince.subscription.business.command.AddProductContributionToPurchaseCostRevenueAndProfitCommand;
import com.affaince.subscription.business.command.domain.BusinessAccount;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 29-01-2017.
 */
@Component
public class AddProductContributionToPurchaseCostRevenueAndProfitCommandHandler {
    private final Repository<BusinessAccount> repository;

    @Autowired
    public AddProductContributionToPurchaseCostRevenueAndProfitCommandHandler(Repository<BusinessAccount> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(AddProductContributionToPurchaseCostRevenueAndProfitCommand command){
        BusinessAccount businessAccount=repository.load(command.getBusinessAccountId());
        businessAccount.updatePurchaseCostRevenueAndProfit(command.getProductId(),command.getPurchaseCostContribution(),command.getRevenueContribution(),command.getProfitContribution());
    }
}
