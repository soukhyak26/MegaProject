package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.product.command.UpdateForecastFromActualsCommand;
import com.affaince.subscription.product.domain.ProductAnalyser;
import com.affaince.subscription.product.query.predictions.ProductHistoryRetriever;
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
    private Repository<ProductAnalyser> repository;
    private ProductHistoryRetriever productHistoryRetriever;

    @Autowired
    public UpdateForecastFromActualsCommandHandler(Repository<ProductAnalyser> repository,ProductHistoryRetriever productHistoryRetriever) {
        this.repository = repository;
        this.productHistoryRetriever = productHistoryRetriever;
    }

    @CommandHandler
    public void handle(UpdateForecastFromActualsCommand command) {
/*
        Product product = repository.load(command.getProductId());
        product.updateForecastFromActuals(command.getForecastDate(), builder);
*/
        ProductAnalyser productAnalyser=repository.load(command.getProductAnalyserId());
        productAnalyser.initiateForecast(command.getProductId(),command.getEntityMetricType(),productHistoryRetriever);
    }
}
