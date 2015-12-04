package com.affaince.subscription.product.registration.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.product.registration.command.*;
import com.affaince.subscription.product.registration.query.repository.ProductRepository;
import com.affaince.subscription.product.registration.query.view.ProductView;
import com.affaince.subscription.product.registration.web.exception.ProductNotFoundException;
import com.affaince.subscription.product.registration.web.request.*;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import java.util.UUID;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
@RestController
@RequestMapping(value = "/product")
@Component
public class ProductController {

    private final SubscriptionCommandGateway commandGateway;
    private final ProductRepository repository;

    @Autowired
    public ProductController(SubscriptionCommandGateway commandGateway, ProductRepository repository) {
        this.commandGateway = commandGateway;
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.POST)
    @Consumes("application/json")
    public ResponseEntity<Object> createItem(@RequestBody @Valid CreateProductRequest request) throws Exception {
        final CreateProductCommand createCommand = new CreateProductCommand(
                UUID.randomUUID().toString(),
                request.getBatchId(),
                request.getCategoryId(),
                request.getCategoryName(),
                request.getSubCategoryId(),
                request.getSubCategoryName(),
                request.getProductId(),
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
    public ResponseEntity<Object> addProjecttionParameters(@RequestBody @Valid AddProjectionParametersRequest request,
                                                           @PathVariable String productId) throws Exception {
        ProductView productView = repository.findOne(productId);
        if (productView == null) {
            throw ProductNotFoundException.build(productId);
        }
        final AddProjectionParametersCommand command = new AddProjectionParametersCommand(
                productId,
                request.getTargetConsumptionPeriod(),
                request.getTargetConsumptionPeriodUnit(),
                request.getTargetSalePerConsumptionPeriod(),
                request.getMinimumProfitMargin(),
                request.getMaximumProfitMargin(),
                request.getDemandToSupplyRatio(),
                request.getConsumptionFrequency(),
                request.getConsumptionFrequencyPeriod(),
                request.getConsumptionFrequencyPeriodUnitCode()
        );
        try {
            commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "ruleparameters/{productId}")
    @Consumes("application/json")
    public ResponseEntity<Object> addSubscriptionableItemRuleParameters(@RequestBody @Valid AddRuleParametersRequest request, @PathVariable String productId) throws Exception {
        ProductView productView = repository.findOne(productId);
        if (productView == null) {
            throw ProductNotFoundException.build(productId);
        }
        final AddProductRuleParametersCommand command = new AddProductRuleParametersCommand(
                productId,
                request.getMinPermissibleDiscount(),
                request.getMaxPermissibleDiscount(),
                request.getDiscountUnitCode(),
                request.getMaxPermissibleUnits(),
                request.getMaxPermissibleSubscriptionPeriod(),
                request.getMaxPermissibleSubscriptionPeriodUnitCode()
        );
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
