package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.product.command.SetProductPricingConfigurationCommand;
import com.affaince.subscription.product.domain.Product;
import com.affaince.subscription.product.domain.exception.ProductDeactivatedException;
import com.affaince.subscription.product.web.exception.InvalidProductStatusException;
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
    public void on(SetProductPricingConfigurationCommand command) throws InvalidProductStatusException, ProductDeactivatedException {
        final Product product = repository.load(command.getProductId());
        product.setProductPricingConfiguration(command);
    }
}
