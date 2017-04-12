package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.date.SysDateTime;
import com.affaince.subscription.product.command.CalculatePriceOfAProductCommand;
import com.affaince.subscription.product.command.domain.Product;
import com.affaince.subscription.product.factory.PriceBucketFactory;
import com.affaince.subscription.product.services.pricing.determinator.DefaultPriceDeterminator;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 17-08-2016.
 */
@Component
public class CalculatePriceOfAProductCommandHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterProductCommandHandler.class);
    private Repository<Product> repository;
    @Autowired
    private DefaultPriceDeterminator defaultPriceDeterminator;

    @Autowired
    public CalculatePriceOfAProductCommandHandler(Repository<Product> repository) {
        this.repository = repository;
    }


    @CommandHandler
    public void handle(CalculatePriceOfAProductCommand command) {
        Product product = repository.load(command.getProductId());
        product.calculatePrice(command.getProductId(), SysDateTime.now(),defaultPriceDeterminator,product.getProductConfiguration().getPricingOptions(),command.getProductDemandTrend());
    }
}
