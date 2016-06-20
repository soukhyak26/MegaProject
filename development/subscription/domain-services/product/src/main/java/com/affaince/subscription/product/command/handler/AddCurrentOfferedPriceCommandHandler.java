package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.product.command.AddCurrentOfferedPriceCommand;
import com.affaince.subscription.product.command.domain.Product;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 07-08-2015.
 */
@Component
public class AddCurrentOfferedPriceCommandHandler {

    private final Repository<Product> repository;

    @Autowired
    public AddCurrentOfferedPriceCommandHandler(Repository<Product> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(AddCurrentOfferedPriceCommand command) {
        Product product = repository.load(command.getProductId());
        product.addCurrentOfferedPrice(command.getCurrentOfferedPrice());
    }
}
