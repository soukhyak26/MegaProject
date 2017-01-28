package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.product.command.CalculateRevenueAndProfitCommand;
import com.affaince.subscription.product.command.domain.Product;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;

/**
 * Created by mandar on 28-01-2017.
 */
public class CalculateRevenueAndProfitCommandHandler {
    private final Repository<Product> repository;

    public CalculateRevenueAndProfitCommandHandler(Repository<Product> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(CalculateRevenueAndProfitCommand command){
        Product product = repository.load(command.getProductId());
        product.getProductAccount().calculateRegisteredRevenueAndProfit(command.getProductId());
    }
}
