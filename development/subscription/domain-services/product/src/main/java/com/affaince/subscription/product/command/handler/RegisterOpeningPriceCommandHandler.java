package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.product.command.RegisterOpeningPriceCommand;
import com.affaince.subscription.product.domain.Product;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 16-10-2016.
 */
@Component
public class RegisterOpeningPriceCommandHandler {
    private final Repository<Product> repository;
    @Autowired
    public RegisterOpeningPriceCommandHandler(Repository<Product> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(RegisterOpeningPriceCommand command) {
        Product product = repository.load(command.getProductId());
        product.getProductAccount().registerOpeningPrice(command.getProductId(),command.getOpeningPrice(),command.getFromDate());
    }
}
