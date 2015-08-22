package com.affaince.subscription.subscriptionableitem.registration.command.handler;

import com.affaince.subscription.subscriptionableitem.registration.command.UpdatePriceAndStockParametersCommand;
import com.affaince.subscription.subscriptionableitem.registration.command.domain.SubscriptionableItem;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 25-07-2015.
 */
@Component
public class UpdatePriceAndStockParametersCommandHandler {

    private final Repository<SubscriptionableItem> repository;

    @Autowired
    public UpdatePriceAndStockParametersCommandHandler(Repository<SubscriptionableItem> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(UpdatePriceAndStockParametersCommand command) {
        SubscriptionableItem item = repository.load(command.getItemId());
        item.updatePriceAndStockParemeters(command);
    }
}
