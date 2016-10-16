package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.product.command.UpdateProductSubscriptionCommand;
import com.affaince.subscription.product.command.domain.Product;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 19-06-2016.
 */
@Component
public class UpdateProductSubscriptionCommandHandler {
    private final Repository<Product> repository;

    @Autowired
    public UpdateProductSubscriptionCommandHandler(Repository<Product> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void on(UpdateProductSubscriptionCommand command){
        final Product product = repository.load(command.getProductId());
        product.getProductAccount().updateProductSubscription(command);
    }
}
