package com.affaince.subscription.product.command.handler;

import com.affaince.subscription.product.command.DetectChangeInProductTrendCommand;
import com.affaince.subscription.product.command.domain.ProductAnalyser;
import com.affaince.subscription.product.services.trend.ProductTrendChangeDetector;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 10/1/2017.
 */
@Component
public class DetectChangeInProductTrendCommandHandler {
    private final Repository<ProductAnalyser> repository;
    private final ProductTrendChangeDetector productTrendChangeDetector;
    @Autowired
    public DetectChangeInProductTrendCommandHandler(Repository<ProductAnalyser> repository, ProductTrendChangeDetector productTrendChangeDetector) {
        this.repository = repository;
        this.productTrendChangeDetector = productTrendChangeDetector;
    }

    @CommandHandler
    public void handle(DetectChangeInProductTrendCommand command){
        ProductAnalyser productAnalyser=repository.load(command.getProductAnalyserId());
        productAnalyser.analyseProductTrendChange(command.getProductId(),command.getEntityMetadata(),command.getForecastDate(),productTrendChangeDetector);
    }
}
