package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.product.command.UpdateForecastFromActualsCommand;
import com.affaince.subscription.product.command.domain.Product;
import com.affaince.subscription.product.services.forecast.ForecastFinderService;
import com.affaince.subscription.product.services.forecast.ProductDemandForecastBuilder;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateForecastFromActualsCommandHandler.class);
    private Repository<Product> repository;
    //private DemandForecasterChain demandForecasterChain;
    private final ProductDemandForecastBuilder builder;

    @Autowired
    public UpdateForecastFromActualsCommandHandler(Repository<Product> repository, ProductDemandForecastBuilder builder) {
        this.repository = repository;
        this.builder = builder;
    }

    @CommandHandler
    public void handle(UpdateForecastFromActualsCommand command) {
        Product product = repository.load(command.getProductId());
        product.updateForecastFromActuals(command.getForecastDate(), builder);
    }
}
