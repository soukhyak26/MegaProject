package com.affaince.subscription.subscriptionableitem.registration.web.controller;

import com.affaince.subscription.subscriptionableitem.registration.command.AddProjectionParametersCommand;
import com.affaince.subscription.subscriptionableitem.registration.command.AddSubscriptionableItemRuleParametersCommand;
import com.affaince.subscription.subscriptionableitem.registration.command.CreateSubscriptionableItemCommand;
import com.affaince.subscription.subscriptionableitem.registration.command.UpdatePriceAndStockParametersCommand;
import com.affaince.subscription.subscriptionableitem.registration.web.request.AddProjectionParametersRequest;
import com.affaince.subscription.subscriptionableitem.registration.web.request.AddSubscriptionableItemRuleParametersRequest;
import com.affaince.subscription.subscriptionableitem.registration.web.request.CreateSubscriptionableItemRequest;
import com.affaince.subscription.subscriptionableitem.registration.web.request.UpdatePriceAndStockParametersRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
                request.getGetSubCategoryNmae(),
                request.getProductId(),
                request.getCurrentMRP(),
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
    public ResponseEntity<Object> AddProjecttionParameters(@RequestBody AddProjectionParametersRequest request, @PathParam("itemid") String itemId) {
        AddProjectionParametersCommand command = new AddProjectionParametersCommand(
                itemId,
                request.getTargetConsumptionPeriod(),
                request.getTargetConsumptionPeriodUnit(),
                request.getTargetSalePerConsumptionPeriod(),
                request.getMinimumProfitMargin(),
                request.getMaximumProfitMargin(),
                request.getDemandToSupplyRatio(),
                request.getConsumptionFrequency()
        );
        commandGateway.sendAndWait(command);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "addsubscriptionableitemruleparameters/{itemid}")
    @Consumes("application/json")
    public ResponseEntity<Object> AddSubscriptionableItemRuleParameters(@RequestBody AddSubscriptionableItemRuleParametersRequest request, @PathParam("itemid") String itemId) {
        AddSubscriptionableItemRuleParametersCommand command = new AddSubscriptionableItemRuleParametersCommand(
                itemId,
                request.getMinPermissibleDiscount(),
                request.getMaxPermissibleDiscount(),
                request.getMaxPermissibleUnits(),
                request.getMaxPermissibleSubscriptionPeriod(),
                request.getMaxPermissibleSubscriptionPeriodUnit()
        );
        commandGateway.sendAndWait(command);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
