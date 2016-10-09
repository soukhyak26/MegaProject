package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.product.command.RegisterProductCommand;
import com.affaince.subscription.product.command.domain.Product;
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
public class RegisterProductCommandHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterProductCommandHandler.class);
    private Repository<Product> repository;

    @Autowired
    public RegisterProductCommandHandler(Repository<Product> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(RegisterProductCommand command) {
        Product product = new Product(
                command.getProductId(),
                command.getProductName(),
                command.getCategoryId(),
                command.getSubCategoryId(),
                command.getQuantity(),
                command.getQuantityUnit(),
                command.getSubstitutes(),
                command.getComplements(),
                command.getSensitiveTo(),
                command.getProductPricingCategory()
        );
        repository.add(product);
        LOGGER.info("Product added to write repository with Name: " + command.getProductName() + " category:" + command.getCategoryId() + " subcategory: " + command.getSubCategoryId() + " on date: " + SysDate.now());
    }
}
