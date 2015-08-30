package com.affaince.subscription.subscriptionableitem.registration.web.controller;

import com.affaince.subscription.subscriptionableitem.registration.command.*;
import com.affaince.subscription.subscriptionableitem.registration.web.request.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import java.util.UUID;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
@RestController
@RequestMapping(value = "/subscriptionableitem")
@Component
public class SubscriptionableItemController {

    private final CommandGateway commandGateway;

    @Autowired
    public SubscriptionableItemController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @RequestMapping(method = RequestMethod.POST)
    @Consumes("application/json")
    public ResponseEntity<Object> createItem(@RequestBody CreateSubscriptionableItemRequest request) {
        CreateSubscriptionableItemCommand createCommand = new CreateSubscriptionableItemCommand(
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
        commandGateway.sendAndWait(createCommand);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{itemid}")
    @Consumes("application/json")
    public ResponseEntity<Object> updatePriceAndStockParameters(@RequestBody UpdatePriceAndStockParametersRequest request, @PathParam("itemid") String itemId) {
        UpdatePriceAndStockParametersCommand command = new UpdatePriceAndStockParametersCommand(
                itemId,
                request.getCurrentMRP(),
                request.getCurrentStockInUnits(),
                LocalDate.now()
        );
        commandGateway.sendAndWait(command);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "addprojectionparameters/{itemid}")
    @Consumes("application/json")
    public ResponseEntity<Object> addProjecttionParameters(@RequestBody AddProjectionParametersRequest request,
                                                           @PathVariable ("itemid") String itemId) {
        AddProjectionParametersCommand command = new AddProjectionParametersCommand(
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
        commandGateway.sendAndWait(command);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "addsubscriptionableitemruleparameters/{itemid}")
    @Consumes("application/json")
    public ResponseEntity<Object> addSubscriptionableItemRuleParameters(@RequestBody AddSubscriptionableItemRuleParametersRequest request, @PathVariable("itemid") String itemId) {
        System.out.println ("@@@@@@@@@@@@@@@@@@@" + itemId);
        AddSubscriptionableItemRuleParametersCommand command = new AddSubscriptionableItemRuleParametersCommand(
                itemId,
                request.getMinPermissibleDiscount(),
                request.getMaxPermissibleDiscount(),
                request.getDiscountUnitCode(),
                request.getMaxPermissibleUnits(),
                request.getMaxPermissibleSubscriptionPeriod(),
                request.getMaxPermissibleSubscriptionPeriodUnitCode()
        );
        commandGateway.sendAndWait(command);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/setcurrentofferedprice/{itemid}")
    @Consumes("application/json")
    public ResponseEntity<Object> setCurrentOfferedPrice(@PathVariable("itemid") String itemId, @RequestBody AddCurrentOfferedPriceRequest request) {
        AddCurrentOfferedPriceCommand command = new AddCurrentOfferedPriceCommand(itemId, request.getCurrentOfferedPrice());
        commandGateway.sendAndWait(command);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
