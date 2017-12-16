package com.affaince.subscription.business.command.handler;

import com.affaince.subscription.business.command.DonateExcessProfitForAProductToNodalAccountCommand;
import com.affaince.subscription.business.domain.BusinessAccount;
import com.affaince.subscription.date.SysDate;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 30-01-2017.
 */
@Component
public class DonateExcessProfitForAProductToNodalAccountCommandHandler {
    private Repository<BusinessAccount> repository;

    @Autowired
    public DonateExcessProfitForAProductToNodalAccountCommandHandler(Repository<BusinessAccount> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(DonateExcessProfitForAProductToNodalAccountCommand command) {
        BusinessAccount businessAccount = repository.load(command.getBusinessAccountId());
        businessAccount.addToNodalAccount(command.getProductId(),command.getExcessProfit(), SysDate.now());
    }
}

