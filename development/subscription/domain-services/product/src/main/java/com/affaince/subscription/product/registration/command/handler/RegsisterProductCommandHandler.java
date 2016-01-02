package com.affaince.subscription.product.registration.command.handler;

import com.affaince.subscription.product.registration.command.RegisterProductCommand;
import com.affaince.subscription.product.registration.command.domain.Product;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 25-07-2015.
 */

@Component
public class RegsisterProductCommandHandler {

    private Repository<Product> repository;
    private static final Logger LOGGER = LoggerFactory.getLogger(RegsisterProductCommandHandler.class);

    @Autowired
    public RegsisterProductCommandHandler(Repository<Product> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(RegisterProductCommand command) {
        Product product = new Product(
                command.getProductId(),
                command.getProductName(),
                command.getCategoryId(),
                command.getSubCategoryId()
        );
        repository.add(product);
        LOGGER.info("Product added to write repository with id: " + command.getProductId());
    }
}
