package com.affaince.subscription.product.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.product.command.CalculatePriceOfAProductCommand;
import com.affaince.subscription.product.query.repository.ProductViewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;

/**
 * Created by mandar on 14-08-2016.
 */
@RestController
@RequestMapping(value = "/pricing")
@Component

public class PricingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final SubscriptionCommandGateway commandGateway;
    private final ProductViewRepository productViewRepository;


    @Autowired
    public PricingController(SubscriptionCommandGateway commandGateway, ProductViewRepository productViewRepository) {
        this.commandGateway = commandGateway;
        this.productViewRepository = productViewRepository;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{productid}/{productdemandtrend}")
    @Consumes("application/json")
    public ResponseEntity<String> calculatePrice(@PathVariable("productid") String productId, @PathVariable("productdemandtrend") String productDemandTrend) throws Exception {
        CalculatePriceOfAProductCommand command = new CalculatePriceOfAProductCommand(productId, ProductDemandTrend.valueOf(Integer.parseInt(productDemandTrend)));
        commandGateway.executeAsync(command);
        return new ResponseEntity<String>(productId, HttpStatus.OK);
    }
}
