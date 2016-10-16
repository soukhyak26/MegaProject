package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.product.command.UpdatePseudoActualsFromActualsCommand;
import com.affaince.subscription.product.command.domain.Product;
import com.affaince.subscription.product.services.forecast.ProductDemandForecastBuilder;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 14-08-2016.
 */
@Component
public class UpdatePseudoActualsFromActualsCommandHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterProductCommandHandler.class);
    private Repository<Product> repository;
    private ProductDemandForecastBuilder builder;

    @Autowired
    public UpdatePseudoActualsFromActualsCommandHandler(Repository<Product> repository, ProductDemandForecastBuilder builder) {
        this.repository = repository;
        this.builder = builder;
    }

    @CommandHandler
    public void handle(UpdatePseudoActualsFromActualsCommand command) {
        Product product = repository.load(command.getProductId());
        product.updatePseudoActualsFromActuals(command.getForecastDate(), builder);
    }

}
