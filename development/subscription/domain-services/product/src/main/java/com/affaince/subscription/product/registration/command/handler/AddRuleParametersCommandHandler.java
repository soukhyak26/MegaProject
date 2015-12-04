package com.affaince.subscription.product.registration.command.handler;

import com.affaince.subscription.product.registration.command.AddProductRuleParametersCommand;
import com.affaince.subscription.product.registration.command.domain.Product;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 26-07-2015.
 */
@Component
public class AddRuleParametersCommandHandler {

    private final Repository<Product> repository;

    @Autowired
    public AddRuleParametersCommandHandler(Repository<Product> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(AddProductRuleParametersCommand command) {
        Product product = repository.load(command.getItemId());
        product.addSubscriptionableItemRuleParameters(command);
    }
}
