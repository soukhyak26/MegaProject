package com.affaince.subscription.subscriptionableitem.registration.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.subscriptionableitem.registration.command.*;
import com.affaince.subscription.subscriptionableitem.registration.query.repository.SubscriptionableItemRepository;
import com.affaince.subscription.subscriptionableitem.registration.query.view.SubscriptionableItemView;
import com.affaince.subscription.subscriptionableitem.registration.web.exception.SubscriptionableItemNotFoundException;
import com.affaince.subscription.subscriptionableitem.registration.web.request.*;
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
@RequestMapping(value = "/subscriptionableitem")
@Component
public class SubscriptionableItemController {

    private final SubscriptionCommandGateway commandGateway;
    private final SubscriptionableItemRepository repository;

    @Autowired
    public SubscriptionableItemController(SubscriptionCommandGateway commandGateway, SubscriptionableItemRepository repository) {
        this.commandGateway = commandGateway;
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.POST)
    @Consumes("application/json")
    public ResponseEntity<Object> createItem(@RequestBody @Valid CreateSubscriptionableItemRequest request) throws Exception {
        final CreateSubscriptionableItemCommand createCommand = new CreateSubscriptionableItemCommand(
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

    @RequestMapping(method = RequestMethod.PUT, value = "/{itemid}")
    @Consumes("application/json")
    public ResponseEntity<Object> updatePriceAndStockParameters(@RequestBody @Valid UpdatePriceAndStockParametersRequest request, @PathVariable("itemid") String itemId) throws Exception {
        SubscriptionableItemView subscriptionableItemView = repository.findOne(itemId);
        if (subscriptionableItemView == null) {
            throw SubscriptionableItemNotFoundException.build(itemId);
        }
        final UpdatePriceAndStockParametersCommand command = new UpdatePriceAndStockParametersCommand(
                itemId,
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

    @RequestMapping(method = RequestMethod.PUT, value = "addprojectionparameters/{itemid}")
    @Consumes("application/json")
    public ResponseEntity<Object> addProjecttionParameters(@RequestBody @Valid AddProjectionParametersRequest request,
                                                           @PathVariable("itemid") String itemId) throws Exception {
        SubscriptionableItemView subscriptionableItemView = repository.findOne(itemId);
        if (subscriptionableItemView == null) {
            throw SubscriptionableItemNotFoundException.build(itemId);
        }
        final AddProjectionParametersCommand command = new AddProjectionParametersCommand(
                itemId,
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

    @RequestMapping(method = RequestMethod.PUT, value = "addsubscriptionableitemruleparameters/{itemid}")
    @Consumes("application/json")
    public ResponseEntity<Object> addSubscriptionableItemRuleParameters(@RequestBody @Valid AddSubscriptionableItemRuleParametersRequest request, @PathVariable("itemid") String itemId) throws Exception {
        SubscriptionableItemView subscriptionableItemView = repository.findOne(itemId);
        if (subscriptionableItemView == null) {
            throw SubscriptionableItemNotFoundException.build(itemId);
        }
        final AddSubscriptionableItemRuleParametersCommand command = new AddSubscriptionableItemRuleParametersCommand(
                itemId,
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

    @RequestMapping(method = RequestMethod.PUT, value = "/setcurrentofferedprice/{itemid}")
    @Consumes("application/json")
    public ResponseEntity<Object> setCurrentOfferedPrice(@PathVariable("itemid") String itemId, @RequestBody @Valid AddCurrentOfferedPriceRequest request) throws Exception {
        SubscriptionableItemView subscriptionableItemView = repository.findOne(itemId);
        if (subscriptionableItemView == null) {
            throw SubscriptionableItemNotFoundException.build(itemId);
        }
        final AddCurrentOfferedPriceCommand command = new AddCurrentOfferedPriceCommand(itemId, request.getCurrentOfferedPrice());
        try {
            commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
