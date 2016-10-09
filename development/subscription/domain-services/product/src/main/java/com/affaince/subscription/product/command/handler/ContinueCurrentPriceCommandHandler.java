package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.product.command.ContinueCurrentPriceCommand;
import com.affaince.subscription.product.command.domain.Product;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 09-10-2016.
 */
@Component
public class ContinueCurrentPriceCommandHandler {

    private final Repository<Product> repository;

    @Autowired
    public ContinueCurrentPriceCommandHandler(Repository<Product> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(ContinueCurrentPriceCommand command) {
        Product product = repository.load(command.getProductId());
        product.continueCurrentPrice();
    }
}
