package com.affaince.subscription.consumerbasket.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.consumerbasket.command.BasketDeletedCommand;
import com.affaince.subscription.consumerbasket.command.CreateBasketCommand;
import com.affaince.subscription.consumerbasket.command.ItemDispatchStatus;
import com.affaince.subscription.consumerbasket.command.UpdateStatusAndDispatchDateCommand;
import com.affaince.subscription.consumerbasket.query.repository.BasketRepository;
import com.affaince.subscription.consumerbasket.query.view.BasketView;
import com.affaince.subscription.consumerbasket.web.exception.BasketNotFoundException;
import com.affaince.subscription.consumerbasket.web.request.BasketDispatchRequest;
import com.affaince.subscription.consumerbasket.web.request.BasketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by rbsavaliya on 02-10-2015.
 */
@RestController
@RequestMapping(value = "basket")
public class BasketController {

    private final SubscriptionCommandGateway commandGateway;
    private final BasketRepository repository;

    @Autowired
    public BasketController(SubscriptionCommandGateway commandGateway, BasketRepository repository) {
        this.commandGateway = commandGateway;
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> createBasket(@RequestBody BasketRequest request) throws Exception {
        final CreateBasketCommand command = new CreateBasketCommand(UUID.randomUUID().toString(),
                Arrays.asList(request.getDeliveryItems()), request.getDeliveryDate()
        );
        try {
            commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "updatestatusanddispatchdate/{basketId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateStatusAndDispatchDate(@RequestBody BasketDispatchRequest request,
                                                              @PathVariable String basketId) throws Exception {
        final BasketView basketView = repository.findOne(basketId);
        if (basketView == null) {
            throw BasketNotFoundException.build(basketId);
        }
        final UpdateStatusAndDispatchDateCommand command = new UpdateStatusAndDispatchDateCommand(
                basketId, request.getBasketDeliveryStatus(), request.getDispatchDate(),
                Arrays.stream(request.getItemStatusRequest()).map(itemStatus -> new ItemDispatchStatus(itemStatus.getItemId(),
                        itemStatus.getItemDeliveryStatus())).collect(Collectors.toList())
        );
        try {
            commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(value = "delete/{basketId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteBasket(@PathVariable String basketId) throws Exception {
        final BasketView basketView = repository.findOne(basketId);
        if (basketView == null) {
            throw BasketNotFoundException.build(basketId);
        }
        final BasketDeletedCommand command = new BasketDeletedCommand(basketId);
        try {
            commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
