package com.affaince.subscription.product.registration.command.handler;

import com.affaince.subscription.product.registration.command.UpdateProductSubscriptionCommand;
import com.affaince.subscription.product.registration.command.domain.Product;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;

/**
 * Created by mandar on 19-06-2016.
 */
public class UpdateProductSubscriptionCommandHandler {
    private final Repository<Product> repository;

    public UpdateProductSubscriptionCommandHandler(Repository<Product> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void on(UpdateProductSubscriptionCommand command){
        final Product product = repository.load(command.getProductId());
        product.getProductAccount().updateProductSubscription(command);
    }
}
