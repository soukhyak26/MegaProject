package com.affaince.subscription.subscriptionableitem.registration.web.controller;

import com.affaince.subscription.subscriptionableitem.registration.command.CreateSubscriptionableItemCommand;
import com.affaince.subscription.subscriptionableitem.registration.web.request.CreateSubscriptionableItemRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import java.util.UUID;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
@RestController
@RequestMapping (value = "/subscriptionableitem")
@Component
public class SubscriptionableItemController {

    private final CommandGateway commandGateway;

    @Autowired
    public SubscriptionableItemController (CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @RequestMapping (method = RequestMethod.POST)
    @Consumes ("application/json")
    public ResponseEntity<Object> createItem (@RequestBody CreateSubscriptionableItemRequest request) {
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
                request.getCurrentPrizeDate()
        );
        commandGateway.sendAndWait(createCommand);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }
}
