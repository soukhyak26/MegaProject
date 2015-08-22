package com.affaince.subscription.subscriptionableitem.registration.command.handler;

import com.affaince.subscription.subscriptionableitem.registration.command.AddCurrentOfferedPriceCommand;
import com.affaince.subscription.subscriptionableitem.registration.command.domain.SubscriptionableItem;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 07-08-2015.
 */
@Component
public class AddCurrentOfferedPriceCommandHandler {

    private final Repository<SubscriptionableItem> repository;

    @Autowired
    public AddCurrentOfferedPriceCommandHandler(Repository<SubscriptionableItem> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(AddCurrentOfferedPriceCommand command) {
        SubscriptionableItem subscriptionableItem = repository.load(command.getItemId());
        subscriptionableItem.addCurrentOfferedPrice(command.getCurrentOfferedPrice());
    }
}
