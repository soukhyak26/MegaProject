package com.affaince.subscription.product.registration.command.handler;

import com.affaince.subscription.product.registration.command.SetProductConfigurationCommand;
import com.affaince.subscription.product.registration.command.domain.Product;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
@Component
public class SetProductConfigurationCommandHandler {

    private final Repository<Product> repository;

    @Autowired
    public SetProductConfigurationCommandHandler(Repository<Product> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void on(SetProductConfigurationCommand command) {
        final Product product = repository.load(command.getProductId());
        product.setProductConfiguration(command);
    }
}
