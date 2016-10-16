package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.product.command.UpdateFixedExpenseToProductCommand;
import com.affaince.subscription.product.command.domain.Product;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 16-10-2016.
 */
@Component
public class UpdateFixedExpenseToProductCommandHandler {
    private final Repository<Product> repository;

    @Autowired
    public UpdateFixedExpenseToProductCommandHandler(Repository<Product> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void on(UpdateFixedExpenseToProductCommand command) {
        final Product product = repository.load(command.getProductId());
        product.updateFixedExpenses(command);
    }
}
