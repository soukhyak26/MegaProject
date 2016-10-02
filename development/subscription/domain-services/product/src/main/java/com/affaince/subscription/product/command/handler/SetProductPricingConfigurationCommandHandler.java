package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.product.command.SetProductPricingConfigurationCommand;
import com.affaince.subscription.product.command.domain.Product;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
@Component
public class SetProductPricingConfigurationCommandHandler {

    private final Repository<Product> repository;

    @Autowired
    public SetProductPricingConfigurationCommandHandler(Repository<Product> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void on(SetProductPricingConfigurationCommand command) {
        final Product product = repository.load(command.getProductId());
        product.setProductPricingConfiguration(command);
    }
}