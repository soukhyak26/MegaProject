package com.affaince.subscription.product.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.product.command.SetProductPricingConfigurationCommand;
import com.affaince.subscription.product.query.repository.ProductViewRepository;
import com.affaince.subscription.product.query.view.ProductView;
import com.affaince.subscription.product.web.exception.ProductNotFoundException;
import com.affaince.subscription.product.web.request.ProductPricingConfigurationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Consumes;

/**
 * Created by mandar on 12-09-2016.
 */
@RestController
@RequestMapping(value = "/productconfig")
@Component
public class ProductConfigurationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final SubscriptionCommandGateway commandGateway;
    private final ProductViewRepository productViewRepository;

    @Autowired
    public ProductConfigurationController(SubscriptionCommandGateway commandGateway, ProductViewRepository productViewRepository) {
        this.commandGateway = commandGateway;
        this.productViewRepository = productViewRepository;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/pricingconfig/{productId}")
    @Consumes("application/json")
    public ResponseEntity<Object> setProductPricingConfiguration(@PathVariable String productId, @RequestBody @Valid ProductPricingConfigurationRequest request) throws Exception {
        final ProductView productView = this.productViewRepository.findOne(productId);
        if (productView == null) {
            throw ProductNotFoundException.build(productId);
        }

        SetProductPricingConfigurationCommand command = new SetProductPricingConfigurationCommand(
                productId, request.getActualsAggregationPeriodForTargetForecast(), request.getTargetChangeThresholdForPriceChange(), request.isCrossPriceElasticityConsidered(), request.isAdvertisingExpensesConsidered(), request.getPricingOptions(), request.getPricingStrategyType(), request.getDemandCurvePeriod());
        try {
            this.commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}
