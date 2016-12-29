package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.product.command.CalculateExpectedProfitPerPriceBucketCommand;
import com.affaince.subscription.product.command.domain.Product;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mandar on 29-12-2016.
 */
public class CalculateExpectedProfitPerPriceBucketCommandHandler {
    private final Repository<Product> repository;

    @Autowired
    public CalculateExpectedProfitPerPriceBucketCommandHandler(Repository<Product> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(CalculateExpectedProfitPerPriceBucketCommand command){
        Product product = repository.load(command.getProductId());
        product.calculateExpectedProfitPerPriceBucket(command);
    }
}
