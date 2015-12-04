package com.affaince.subscription.product.registration.command.handler;

import com.affaince.subscription.product.registration.command.UpdatePriceAndStockParametersCommand;
import com.affaince.subscription.product.registration.command.domain.Product;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 25-07-2015.
 */
@Component
public class UpdatePriceAndStockParametersCommandHandler {

    private final Repository<Product> repository;

    @Autowired
    public UpdatePriceAndStockParametersCommandHandler(Repository<Product> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(UpdatePriceAndStockParametersCommand command) {
        Product item = repository.load(command.getItemId());
        item.updatePriceAndStockParemeters(command);
    }
}
