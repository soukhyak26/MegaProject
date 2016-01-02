package com.affaince.subscription.subscriber.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.subscriber.command.*;
import com.affaince.subscription.subscriber.query.repository.SubscriptionViewRepository;
import com.affaince.subscription.subscriber.query.view.SubscriptionView;
import com.affaince.subscription.subscriber.web.exception.ConsumerBasketNotFoundException;
import com.affaince.subscription.subscriber.web.request.AddressRequest;
import com.affaince.subscription.subscriber.web.request.BasketItemRequest;
import com.affaince.subscription.subscriber.web.request.ContactDetailsRequest;
import com.affaince.subscription.subscriber.web.request.SubscriptionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import java.util.UUID;

/**
 * Created by rbsavaliya on 09-08-2015.
 */
@RestController
@RequestMapping(value = "subscription")
public class SubscriptionController {

    private final SubscriptionCommandGateway commandGateway;
    private final SubscriptionViewRepository repository;

    @Autowired
    public SubscriptionController(SubscriptionCommandGateway commandGateway, SubscriptionViewRepository repository) {
        this.commandGateway = commandGateway;
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.POST)
    @Consumes("application/json")
    public ResponseEntity<Object> createSubscription(@RequestBody @Valid SubscriptionRequest request) throws Exception {
        final String subscriptionId = UUID.randomUUID().toString();
        final CreateSubscriptionCommand command = new CreateSubscriptionCommand(subscriptionId, request.getUserId());
        try {
            commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "additem/{subscriptionId}", method = RequestMethod.PUT)
    @Consumes("application/json")
    public ResponseEntity<Object> addItemToConsumerBasket(@PathVariable String subscriptionId,
                                                          @RequestBody @Valid BasketItemRequest request) throws Exception {
        final SubscriptionView subscriptionView = repository.findOne(subscriptionId);
        final AddItemToSubscriptionCommand command = new AddItemToSubscriptionCommand(subscriptionId,
                request.getItemId(), request.getQuantityPerBasket(),
                request.getFrequency(), request.getFrequencyUnit(),
                request.getDiscountedOfferedPrice());
        try {
            commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(value = "addshippingaddress/{subscriptionId}", method = RequestMethod.PUT)
    @Consumes("application/json")
    public ResponseEntity<Object> addShippingAddress(@PathVariable String subscriptionId,
                                                     @RequestBody @Valid AddressRequest request) throws Exception {
        final SubscriptionView subscriptionView = repository.findOne(subscriptionId);
        if (subscriptionView == null) {
            throw ConsumerBasketNotFoundException.build(subscriptionId);
        }
        final AddAddressToSubscriptionCommand addShippingAddressCommand = new AddAddressToSubscriptionCommand(
                subscriptionId, "shipping", request.getAddressLine1(), request.getAddressLine2(), request.getCity(),
                request.getState(), request.getCountry(), request.getPinCode()
        );
        try {
            commandGateway.executeAsync(addShippingAddressCommand);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(value = "addbillingaddress/{subscriptionId}", method = RequestMethod.PUT)
    @Consumes("application/json")
    public ResponseEntity<Object> addBillingAddress(@PathVariable String subscriptionId,
                                                    @RequestBody @Valid AddressRequest request) throws Exception {
        final SubscriptionView subscriptionView = repository.findOne(subscriptionId);
        if (subscriptionView == null) {
            throw ConsumerBasketNotFoundException.build(subscriptionId);
        }
        final AddAddressToSubscriptionCommand addBillingAddressCommand = new AddAddressToSubscriptionCommand(
                subscriptionId, "billing", request.getAddressLine1(), request.getAddressLine2(), request.getCity(),
                request.getState(), request.getCountry(), request.getPinCode()
        );
        try {
            commandGateway.executeAsync(addBillingAddressCommand);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(value = "addcontactdetails/{subscriptionId}", method = RequestMethod.PUT)
    @Consumes("application/json")
    public ResponseEntity<Object> addContactDetails(@PathVariable String subscriptionId,
                                                    @RequestBody @Valid ContactDetailsRequest request) throws Exception {
        final SubscriptionView subscriptionView = repository.findOne(subscriptionId);
        if (subscriptionView == null) {
            throw ConsumerBasketNotFoundException.build(subscriptionId);
        }
        final AddContactDetailsCommand contactDetailsCommand = new AddContactDetailsCommand(subscriptionId,
                request.getEmail(), request.getMobileNumber(), request.getAlternativeNumber()
        );
        try {
            commandGateway.executeAsync(contactDetailsCommand);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(value = "deleteitem/{subscriptionId}/{itemId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteItem(@PathVariable String basketId, @PathVariable String itemId) throws Exception {
        final SubscriptionView subscriptionView = repository.findOne(basketId);
        if (subscriptionView == null) {
            throw ConsumerBasketNotFoundException.build(basketId);
        }
        final DeleteItemFromSubscriptionCommand command = new DeleteItemFromSubscriptionCommand(basketId, itemId);
        try {
            commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
