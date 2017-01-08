package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.product.command.UpdateFixedExpenseToProductCommand;
import com.affaince.subscription.product.command.domain.Product;
import com.affaince.subscription.product.services.operatingexpense.OperatingExpenseService;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 16-10-2016.
 */
@Component
public class UpdateFixedExpenseToProductCommandHandler {
    private final Repository<Product> repository;
    private final OperatingExpenseService operatingExpenseService;

    public UpdateFixedExpenseToProductCommandHandler(Repository<Product> repository, OperatingExpenseService operatingExpenseService) {
        this.repository = repository;
        this.operatingExpenseService = operatingExpenseService;
    }

    @CommandHandler
    public void on(UpdateFixedExpenseToProductCommand command) {
        final Product product = repository.load(command.getProductId());
        product.updateFixedExpenses(command, operatingExpenseService);
    }
}
