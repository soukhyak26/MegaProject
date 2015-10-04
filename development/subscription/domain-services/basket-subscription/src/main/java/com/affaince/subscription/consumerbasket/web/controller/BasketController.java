package com.affaince.subscription.consumerbasket.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.consumerbasket.command.CreateBasketCommand;
import com.affaince.subscription.consumerbasket.query.repository.ConsumerBasketRepository;
import com.affaince.subscription.consumerbasket.web.request.BasketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.UUID;

/**
 * Created by rbsavaliya on 02-10-2015.
 */
@RestController(value = "basket")
public class BasketController {

    private final SubscriptionCommandGateway commandGateway;
    private final ConsumerBasketRepository repository;

    @Autowired
    public BasketController(SubscriptionCommandGateway commandGateway, ConsumerBasketRepository repository) {
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
}
