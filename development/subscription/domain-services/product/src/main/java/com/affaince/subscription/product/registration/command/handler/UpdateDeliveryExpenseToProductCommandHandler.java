package com.affaince.subscription.product.registration.command.handler;

import com.affaince.subscription.product.registration.command.UpdateDeliveryExpenseToProductCommand;
import com.affaince.subscription.product.registration.command.domain.PriceBucket;
import com.affaince.subscription.product.registration.command.domain.Product;
import com.affaince.subscription.product.registration.command.domain.ProductAccount;
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

    @Autowired
    public UpdateDeliveryExpenseToProductCommandHandler(Repository<Product> productAccountRepository) {
        this.productAccountRepository = productAccountRepository;
    }

    @CommandHandler
    public void handle (UpdateDeliveryExpenseToProductCommand command) {
        final Product product = productAccountRepository.load(command.getProductId());
        product.getProductAccount().updateSubscriptionSpecificExpenses(command);
    }
}
