package com.affaince.subscription.product.registration.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.product.registration.command.*;
import com.affaince.subscription.product.registration.query.repository.ProductViewRepository;
import com.affaince.subscription.product.registration.query.view.ProductView;
import com.affaince.subscription.product.registration.web.exception.ProductNotFoundException;
import com.affaince.subscription.product.registration.web.request.*;
import org.joda.time.LocalDate;
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
 * Created by rbsavaliya on 19-07-2015.
 */
@RestController
@RequestMapping(value = "/product")
@Component
public class ProductController {

    private final SubscriptionCommandGateway commandGateway;
    private final ProductViewRepository repository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    public ProductController(SubscriptionCommandGateway commandGateway, ProductViewRepository repository) {
        this.commandGateway = commandGateway;
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.POST)
    @Consumes("application/json")
    public ResponseEntity<Object> registerProduct(@RequestBody @Valid RegisterProductRequest request) throws Exception {
        final RegisterProductCommand createCommand = new RegisterProductCommand(
                request.getProductId(),
                request.getProductName(),
                request.getCategoryId(),
                request.getSubCategoryId(),
                request.getQuantity(),
                request.getQuantityUnit(),
                request.getSubstitutes(),
                request.getComplements()
        );
        try {
            commandGateway.executeAsync(createCommand);
        } catch (Exception e) {
            throw e;
        }
        LOGGER.info("Create product command send to Command gateway with Id: " + createCommand.getProductId());
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{productId}")
    @Consumes("application/json")
    public ResponseEntity<Object> updateProductStatus(@RequestBody @Valid UpdateProductStatusRequest request, @PathVariable String productId) throws Exception {
        ProductView productView = repository.findOne(productId);
        if (productView == null) {
            throw ProductNotFoundException.build(productId);
        }
        final UpdateProductStatusCommand command = new UpdateProductStatusCommand(
                productId,
                request.getCurrentPurchasePrice(),
                request.getCurrentMRP(),
                request.getCurrentStockInUnits(),
                LocalDate.now()
        );
        try {
            commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "addprojectionparameters/{productId}")
    @Consumes("application/json")
    public ResponseEntity<Object> addProjectionParameters(@RequestBody @Valid AddForecastParametersRequest request,
                                                          @PathVariable String productId) throws Exception {
        ProductView productView = repository.findOne(productId);
        if (productView == null) {
            throw ProductNotFoundException.build(productId);
        }
        AddForecastParametersCommand command = new AddForecastParametersCommand(
                request.getProductId(),
                request.getForecastedPriceParameters()
        );
        commandGateway.executeAsync(command);
        try {
            commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/setcurrentofferedprice/{productId}")
    @Consumes("application/json")
    public ResponseEntity<Object> setCurrentOfferedPrice(@PathVariable String productId, @RequestBody @Valid AddCurrentOfferedPriceRequest request) throws Exception {
        ProductView productView = repository.findOne(productId);
        if (productView == null) {
            throw ProductNotFoundException.build(productId);
        }
        final AddCurrentOfferedPriceCommand command = new AddCurrentOfferedPriceCommand(productId, request.getCurrentOfferedPrice());
        try {
            commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/setproductconfig/{productId}")
    @Consumes("application/json")
    public ResponseEntity<Object> setProductConfiguration(@PathVariable String productId, @RequestBody @Valid ProductConfigurationRequest request) throws Exception {
        ProductView productView = repository.findOne(productId);
        if (productView == null) {
            throw ProductNotFoundException.build(productId);
        }

        final SetProductConfigurationCommand command = new SetProductConfigurationCommand(
                productId, request.getDemandCurvePeriod(), request.getRevenueChangeThresholdForPriceChange(),
                request.isCrossPriceElasticityConsidered(), request.isAdvertisingExpensesConsidered(),
                request.getDemandWiseProfitSharingRules()
        );
        try {
            commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}
