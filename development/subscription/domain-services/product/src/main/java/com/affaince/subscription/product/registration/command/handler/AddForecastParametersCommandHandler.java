package com.affaince.subscription.product.registration.command.handler;

import com.affaince.subscription.product.registration.command.AddForecastParametersCommand;
import com.affaince.subscription.product.registration.command.domain.Product;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by rbsavaliya on 05-12-2015.
 */
public class AddForecastParametersCommandHandler {
    private final Repository<Product> repository;

    @Autowired
    public AddForecastParametersCommandHandler(Repository<Product> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(AddForecastParametersCommand command) {
        Product product = repository.load(command.getProductId());
        product.addForecastParameters(command);
    }
}
