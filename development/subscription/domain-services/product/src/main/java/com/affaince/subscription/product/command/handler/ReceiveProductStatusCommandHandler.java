package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.product.command.ReceiveProductStatusCommand;
import com.affaince.subscription.product.command.domain.Product;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandark on 02-04-2016.
 */
@Component
public class ReceiveProductStatusCommandHandler {
    private final Repository<Product> repository;

    @Autowired
    public ReceiveProductStatusCommandHandler(Repository<Product> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(ReceiveProductStatusCommand command) {
        Product product = repository.load(command.getProductId());
        product.receiveProductStatus(command);

    }
}
