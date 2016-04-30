package com.affaince.subscription.product.registration.command.handler;

import com.affaince.subscription.product.registration.command.RegisterProductCommand;
import com.affaince.subscription.product.registration.command.UpdateForecastFromActualsCommand;
import com.affaince.subscription.product.registration.command.domain.Product;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mandark on 29-04-2016.
 */
public class UpdateForecastFromActualsCommandHandler {

    private Repository<Product> repository;
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterProductCommandHandler.class);

    @Autowired
    public UpdateForecastFromActualsCommandHandler(Repository<Product> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(UpdateForecastFromActualsCommand command) {
        Product product = repository.load(command.getProductId());
       // product.updateForecastFromActuals();
    }
}
