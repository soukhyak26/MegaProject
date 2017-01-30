package com.affaince.subscription.product.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.product.command.*;
import com.affaince.subscription.product.query.repository.ProductViewRepository;
import com.affaince.subscription.product.query.repository.RecommendedPriceBucketViewRepository;
import com.affaince.subscription.product.query.view.RecommendedPriceBucketView;
import com.affaince.subscription.product.vo.PricingChoiceType;
import com.affaince.subscription.product.web.request.CalculatePriceRequest;
import com.affaince.subscription.product.web.request.RegisterOpeningPriceRequest;
import com.affaince.subscription.product.web.request.RegisterPriceChoiceRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

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
    private final RecommendedPriceBucketViewRepository recommendedPriceBucketViewRepository;
    @Autowired
    public PricingController(SubscriptionCommandGateway commandGateway, ProductViewRepository productViewRepository, RecommendedPriceBucketViewRepository recommendedPriceBucketViewRepository) {
        this.commandGateway = commandGateway;
        this.productViewRepository = productViewRepository;
        this.recommendedPriceBucketViewRepository = recommendedPriceBucketViewRepository;
    }


    //should be calculated only by the pricing batch as a background job.
    @RequestMapping(method = RequestMethod.PUT, value = "/openprice/{productid}")
    @Consumes("application/json")
    public ResponseEntity<String> registerOpeningPrice(@PathVariable("productid") String productId, @RequestBody RegisterOpeningPriceRequest request) throws Exception {
        RegisterOpeningPriceCommand command = new RegisterOpeningPriceCommand(productId, request.getOpeningPrice());
        commandGateway.executeAsync(command);
        return new ResponseEntity<String>(productId, HttpStatus.OK);
    }

    //should be calculated only by the pricing batch as a background job.
    @RequestMapping(method = RequestMethod.PUT, value = "/calculate/{productid}")
    @Consumes("application/json")
    public ResponseEntity<String> calculatePrice(@PathVariable("productid") String productId, @RequestBody CalculatePriceRequest request) throws Exception {
        ProductDemandTrend productDemandTrend = request.getProductDemandTrend();
        double weight =request.getWeight();
        CalculatePriceOfAProductCommand command = new CalculatePriceOfAProductCommand(productId, productDemandTrend);
        commandGateway.executeAsync(command);
        DonateToNodalAccountCommand donateToNodalAccountCommand = new DonateToNodalAccountCommand(productId,weight);
        commandGateway.executeAsync(donateToNodalAccountCommand);
        return new ResponseEntity<String>(productId, HttpStatus.OK);
    }

    //should be exposed only if the prompting price option is selected by merchant
    @RequestMapping(method = RequestMethod.GET, value = "/recommend/{productid}")
    @Consumes("application/json")
    public String recommendPriceChange(@PathVariable("productid") String productId) throws JsonProcessingException {
        Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
        RecommendedPriceBucketView recommendedPriceBucketView = recommendedPriceBucketViewRepository.findByProductwisePriceBucketId_ProductId(productId, sort).get(0);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(recommendedPriceBucketView);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/register/{productid}")
    @Consumes("application/json")
    public ResponseEntity<String> registerMerchantPriceSelection(@PathVariable("productid") String productId, @RequestBody RegisterPriceChoiceRequest request) throws Exception {
        PricingChoiceType pricingChoiceType = request.getPricingChoiceType();
        if (pricingChoiceType == PricingChoiceType.ACCEPT_RECOMMENDATION) {
            AcceptRecommendedPriceCommand command = new AcceptRecommendedPriceCommand(productId);
            commandGateway.executeAsync(command);
        } else if (pricingChoiceType == PricingChoiceType.CONTINUE_CURRENT_PRICE) {
            ContinueCurrentPriceCommand command = new ContinueCurrentPriceCommand(productId);
        } else if (pricingChoiceType == PricingChoiceType.OVERRIDE_RECOMMENDATION) {
            OverrideRecommendedPriceCommand command = new OverrideRecommendedPriceCommand(productId, request.getPricingValue());
            commandGateway.executeAsync(command);
        }
        return new ResponseEntity<String>(productId, HttpStatus.OK);
    }

}
