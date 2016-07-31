package com.affaince.subscription.business.command.handler;

import com.affaince.subscription.business.command.ProductStatusReceivedCommand;
import com.affaince.subscription.business.command.domain.BusinessAccount;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 8/5/16.
 */
@Component
public class ProductStatusReceivedCommandHandler {
    private final Repository<BusinessAccount> repository;

    @Autowired
    public ProductStatusReceivedCommandHandler(Repository<BusinessAccount> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(ProductStatusReceivedCommand command) {
        BusinessAccount businessAccount = repository.load(Integer.valueOf(LocalDate.now().getYear()).toString());
        businessAccount.adjustPurchaseCost(command.getTotalPurchaseCost());
    }
}
