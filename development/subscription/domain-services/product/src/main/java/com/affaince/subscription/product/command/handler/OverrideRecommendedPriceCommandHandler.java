package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.product.command.OverrideRecommendedPriceCommand;
import com.affaince.subscription.product.command.domain.Product;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mandar on 08-10-2016.
 */
public class OverrideRecommendedPriceCommandHandler {
    private final Repository<Product> repository;

    @Autowired
    public OverrideRecommendedPriceCommandHandler(Repository<Product> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(OverrideRecommendedPriceCommand command) {
        Product product = repository.load(command.getProductId());
        product.overrideRecommendedPrice(command.getOverriddenPrice());
    }
}