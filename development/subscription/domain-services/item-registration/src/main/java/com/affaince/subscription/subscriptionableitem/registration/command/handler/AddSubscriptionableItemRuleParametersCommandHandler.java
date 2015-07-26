package com.affaince.subscription.subscriptionableitem.registration.command.handler;

import com.affaince.subscription.subscriptionableitem.registration.command.AddSubscriptionableItemRuleParametersCommand;
import com.affaince.subscription.subscriptionableitem.registration.command.domain.SubscriptionableItem;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 26-07-2015.
 */
@Component
public class AddSubscriptionableItemRuleParametersCommandHandler {

    Repository<SubscriptionableItem> repository;

    @Autowired
    public AddSubscriptionableItemRuleParametersCommandHandler (Repository<SubscriptionableItem> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle (AddSubscriptionableItemRuleParametersCommand command) {
        SubscriptionableItem subscriptionableItem = repository.load(command.getItemId());
        subscriptionableItem.addSubscriptionableItemRuleParameters (command);
    }
}
