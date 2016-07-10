package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.product.command.UpdateForecastFromActualsCommand;
import com.affaince.subscription.product.command.domain.Product;
import com.affaince.subscription.product.services.forecast.DemandForecasterChain;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandark on 29-04-2016.
 */
@Component
public class UpdateForecastFromActualsCommandHandler {

    private Repository<Product> repository;
    private DemandForecasterChain demandForecasterChain;
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterProductCommandHandler.class);

    @Autowired
    public UpdateForecastFromActualsCommandHandler(Repository<Product> repository, DemandForecasterChain demandForecasterChain) {
        this.repository = repository;
        this.demandForecasterChain = demandForecasterChain;
    }

    @CommandHandler
    public void handle(UpdateForecastFromActualsCommand command) {
        Product product = repository.load(command.getProductId());

        product.updateForecastFromActuals(command.getForecastDate(), demandForecasterChain);
    }
}
