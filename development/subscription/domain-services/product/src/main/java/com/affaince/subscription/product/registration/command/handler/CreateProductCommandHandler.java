package com.affaince.subscription.product.registration.command.handler;

import com.affaince.subscription.product.registration.command.CreateProductCommand;
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
public class CreateProductCommandHandler {

    private Repository<Product> repository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateProductCommandHandler.class);

    @Autowired
    public CreateProductCommandHandler(Repository<Product> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(CreateProductCommand command) {
        Product product = new Product(
                command.getProductId(),
                command.getBatchId(),
                command.getCategoryId(),
                command.getCategoryName(),
                command.getSubCategoryId(),
                command.getGetSubCategoryNmae(),
                command.getCurrentPurchasePricePerUnit(),
                command.getCurrentMRP(),
                command.getCurrentOfferedPrice(),
                command.getCurrentStockInUnits(),
                command.getCurrentPriceDate()
        );
        repository.add(product);
        LOGGER.info("Product added to write repository with id: " + command.getProductId());
    }
}
