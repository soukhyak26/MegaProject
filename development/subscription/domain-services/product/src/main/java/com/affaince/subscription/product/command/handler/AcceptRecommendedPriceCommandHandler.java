package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.product.command.AcceptRecommendedPriceCommand;
import com.affaince.subscription.product.command.domain.Product;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 08-10-2016.
 */
@Component
public class AcceptRecommendedPriceCommandHandler {
    private final Repository<Product> repository;

    @Autowired
    public AcceptRecommendedPriceCommandHandler(Repository<Product> repository) {
        this.repository = repository;
    }

    public void handle(AcceptRecommendedPriceCommand command) {
        Product product = repository.load(command.getProductId());
        product.acceptRecommendedPrice();
    }
}
