package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.product.command.AddSingularManualForecastCommand;
import com.affaince.subscription.product.command.domain.Product;
import com.affaince.subscription.product.services.forecast.ForecastFinderService;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 2/28/2017.
 */
@Component
public class AddSingularManualForecastCommandHandler {
    private final Repository<Product> repository;
    private final ForecastFinderService forecastFinderService;
    @Autowired
    public AddSingularManualForecastCommandHandler(Repository<Product> repository, ForecastFinderService forecastFinderService) {
        this.repository = repository;
        this.forecastFinderService = forecastFinderService;
    }

    @CommandHandler
    public void handle(AddSingularManualForecastCommand command){
        Product product = repository.load(command.getProductId());
       // product.registerSingularManualForecast(command.getStartDate(),command.getEndDate(),command.getPurchasePricePerUnit(),command.getMRP(),command.getNumberOfNewSubscriptions(),command.getNumberOfChurnedSubscriptions(),command.getProductForecastStatus());
    }
}
