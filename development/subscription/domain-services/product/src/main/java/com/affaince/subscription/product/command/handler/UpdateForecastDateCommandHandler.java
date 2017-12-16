package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.product.command.UpdateForecastDateCommand;
import com.affaince.subscription.product.domain.Product;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 6/24/2017.
 */
@Component
public class UpdateForecastDateCommandHandler {
    private final Repository<Product> productAccountRepository;

    @Autowired
    public UpdateForecastDateCommandHandler(Repository<Product> productAccountRepository) {
        this.productAccountRepository = productAccountRepository;
    }

    @CommandHandler
    public void handle(UpdateForecastDateCommand command){
        Product product=productAccountRepository.load(command.getProductId());
        product.getProductConfiguration().updateForecastDate(command.getProductId(),command.getNextForecastDate());
    }
}
