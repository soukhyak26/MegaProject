package com.affaince.subscription.product.registration.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.product.registration.command.AddCurrentOfferedPriceCommand;
import com.affaince.subscription.product.registration.command.AddForecastParametersCommand;
import com.affaince.subscription.product.registration.command.CreateProductCommand;
import com.affaince.subscription.product.registration.command.UpdatePriceAndStockParametersCommand;
import com.affaince.subscription.product.registration.query.repository.ProductRepository;
import com.affaince.subscription.product.registration.query.view.ProductView;
import com.affaince.subscription.product.registration.web.exception.ProductNotFoundException;
import com.affaince.subscription.product.registration.web.request.AddCurrentOfferedPriceRequest;
import com.affaince.subscription.product.registration.web.request.AddForecastParametersRequest;
import com.affaince.subscription.product.registration.web.request.CreateProductRequest;
import com.affaince.subscription.product.registration.web.request.UpdatePriceAndStockParametersRequest;
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
    private final ProductRepository repository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    public ProductController(SubscriptionCommandGateway commandGateway, ProductRepository repository) {
        this.commandGateway = commandGateway;
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.POST)
    @Consumes("application/json")
    public ResponseEntity<Object> createItem(@RequestBody @Valid CreateProductRequest request) throws Exception {
        final CreateProductCommand createCommand = new CreateProductCommand(
                request.getProductId(),
                request.getBatchId(),
                request.getCategoryId(),
                request.getCategoryName(),
                request.getSubCategoryId(),
                request.getSubCategoryName(),
                request.getCurrentPurchasePricePerUnit(),
                request.getCurrentMRP(),
                request.getCurrentOfferedPrice(),
                request.getCurrentStockInUnits(),
                LocalDate.now()
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
    public ResponseEntity<Object> updatePriceAndStockParameters(@RequestBody @Valid UpdatePriceAndStockParametersRequest request, @PathVariable String productId) throws Exception {
        ProductView productView = repository.findOne(productId);
        if (productView == null) {
            throw ProductNotFoundException.build(productId);
        }
        final UpdatePriceAndStockParametersCommand command = new UpdatePriceAndStockParametersCommand(
                productId,
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
                request.getFromDate(),
                request.getToDate(),
                request.getPurchasePricePerUnit(),
                request.getSalePricePerUnit(),
                request.getMRP(),
                request.getNumberOfNewCustomersAssociatedWithAPrice(),
                request.getNumberOfChurnedCustomersAssociatedWithAPrice()
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
}
