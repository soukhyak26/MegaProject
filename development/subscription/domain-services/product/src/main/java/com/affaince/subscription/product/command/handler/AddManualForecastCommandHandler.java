package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.product.command.AddManualForecastCommand;
import com.affaince.subscription.product.command.domain.Product;
import com.affaince.subscription.product.services.forecast.ForecastFinderService;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 06-10-2016.
 */
@Component
public class AddManualForecastCommandHandler {
    private final Repository<Product> repository;
    private final ForecastFinderService forecastFinderService;

    @Autowired
    public AddManualForecastCommandHandler(Repository<Product> repository,ForecastFinderService forecastFinderService) {
        this.repository = repository;
        this.forecastFinderService=forecastFinderService;
    }

    @CommandHandler
    public void handle(AddManualForecastCommand command) {
        Product product = repository.load(command.getProductId());
        product.registerManualForecast(command.getProductForecastParameters(),forecastFinderService,command.getForecastDate());
    }
}
