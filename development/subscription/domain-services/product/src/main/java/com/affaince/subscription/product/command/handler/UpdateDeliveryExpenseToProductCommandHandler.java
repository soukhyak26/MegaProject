package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.product.command.UpdateDeliveryExpenseToProductCommand;
import com.affaince.subscription.product.command.domain.Product;
import com.affaince.subscription.product.services.operatingexpense.OperatingExpenseService;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rsavaliya on 8/5/16.
 */
@Component
public class UpdateDeliveryExpenseToProductCommandHandler {
    private final Repository <Product> productAccountRepository;
    private final OperatingExpenseService operatingExpenseService;

    @Autowired
    public UpdateDeliveryExpenseToProductCommandHandler(Repository<Product> productAccountRepository, OperatingExpenseService operatingExpenseService) {
        this.productAccountRepository = productAccountRepository;
        this.operatingExpenseService = operatingExpenseService;
    }

    @CommandHandler
    public void handle (UpdateDeliveryExpenseToProductCommand command) {
        final Product product = productAccountRepository.load(command.getProductId());
        product.getProductAccount().updateSubscriptionSpecificExpenses(command, operatingExpenseService);
    }
}
