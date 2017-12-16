package com.affaince.subscription.business.command.handler;

import com.affaince.subscription.business.command.DebitFromPurchaseCostAccountPriceOfDeliveredProductsCommand;
import com.affaince.subscription.business.domain.BusinessAccount;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.YearMonth;

/**
 * Created by mandar on 19-02-2017.
 */
@Component
public class DebitFromPurchaseCostAccountPriceOfDeliveredProductsCommandHandler {
    private final Repository<BusinessAccount> repository;
    @Autowired
    public DebitFromPurchaseCostAccountPriceOfDeliveredProductsCommandHandler(Repository<BusinessAccount> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(DebitFromPurchaseCostAccountPriceOfDeliveredProductsCommand command){
        BusinessAccount businessAccount=repository.load(YearMonth.now().getYear());

    }
}
