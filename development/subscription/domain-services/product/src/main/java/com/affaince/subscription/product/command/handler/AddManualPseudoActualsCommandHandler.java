package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.product.command.AddManualPseudoActualsCommand;
import com.affaince.subscription.product.command.domain.Product;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 07-10-2016.
 */
@Component
public class AddManualPseudoActualsCommandHandler {
    private final Repository<Product> repository;

    @Autowired
    public AddManualPseudoActualsCommandHandler(Repository<Product> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(AddManualPseudoActualsCommand command) {
        Product product = repository.load(command.getProductId());
        product.registerManualStepForecast();
    }
}
