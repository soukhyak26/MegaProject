package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.date.SysDateTime;
import com.affaince.subscription.product.command.RecalculateOfferPriceCommand;
import com.affaince.subscription.product.domain.Product;
import com.affaince.subscription.product.services.pricing.determinator.DefaultPriceDeterminator;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mandar on 03-01-2017.
 */
public class RecalculateOfferPriceCommandHandler {
    private final Repository<Product> repository;
    private final DefaultPriceDeterminator defaultPriceDeterminator ;

    @Autowired
    public RecalculateOfferPriceCommandHandler(Repository<Product> repository,DefaultPriceDeterminator defaultPriceDeterminator) {
        this.repository = repository;
        this.defaultPriceDeterminator=defaultPriceDeterminator;
    }

    @CommandHandler
    public void handle(RecalculateOfferPriceCommand command){
        Product product= repository.load(command.getProductId());
        product.calculatePrice(command.getProductId(),SysDateTime.now(),defaultPriceDeterminator,product.getProductConfiguration().getPricingOptions(),product.getProductDemandTrend());
    }
}
