package com.affaince.subscription.subscriber.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.subscriber.command.CreateBasketCommand;
import com.affaince.subscription.subscriber.command.DeleteBasketCommand;
import com.affaince.subscription.subscriber.command.ItemDispatchStatus;
import com.affaince.subscription.subscriber.command.UpdateStatusAndDispatchDateCommand;
import com.affaince.subscription.subscriber.query.repository.BasketRepository;
import com.affaince.subscription.subscriber.query.view.BasketView;
import com.affaince.subscription.subscriber.web.exception.BasketNotFoundException;
import com.affaince.subscription.subscriber.web.request.BasketDispatchRequest;
import com.affaince.subscription.subscriber.web.request.BasketRequest;
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

    @RequestMapping(method = RequestMethod.POST, value = "{subscriberId}")
    public ResponseEntity<Object> createBasket(@RequestBody BasketRequest request, @PathVariable String subscriberId) throws Exception {
        final CreateBasketCommand command = new CreateBasketCommand(subscriberId, UUID.randomUUID().toString(),
                Arrays.asList(request.getDeliveryItems()), request.getDeliveryDate()
        );
        try {
            commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "updatestatusanddispatchdate/{subscriberId}/{basketId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateStatusAndDispatchDate(@RequestBody BasketDispatchRequest request,
                                                              @PathVariable String basketId,
                                                              @PathVariable String subscriberId) throws Exception {
        final BasketView basketView = repository.findOne(basketId);
        if (basketView == null) {
            throw BasketNotFoundException.build(basketId);
        }
        final UpdateStatusAndDispatchDateCommand command = new UpdateStatusAndDispatchDateCommand(
                subscriberId, basketId, request.getBasketDeliveryStatus(), request.getDispatchDate(),
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

    @RequestMapping(value = "delete/{subscriberId}/{basketId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteBasket(@PathVariable String basketId, @PathVariable String subscriberId) throws Exception {
        final BasketView basketView = repository.findOne(basketId);
        if (basketView == null) {
            throw BasketNotFoundException.build(basketId);
        }
        final DeleteBasketCommand command = new DeleteBasketCommand(subscriberId, basketId);
        try {
            commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
