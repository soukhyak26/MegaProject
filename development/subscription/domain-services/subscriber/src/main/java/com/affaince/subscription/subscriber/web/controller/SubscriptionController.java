package com.affaince.subscription.subscriber.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.common.type.QuantityUnit;
import com.affaince.subscription.subscriber.command.*;
import com.affaince.subscription.subscriber.query.repository.ProductViewRepository;
import com.affaince.subscription.subscriber.query.repository.SubscriptionViewRepository;
import com.affaince.subscription.subscriber.query.view.ProductView;
import com.affaince.subscription.subscriber.query.view.SubscriptionView;
import com.affaince.subscription.subscriber.web.exception.ConsumerBasketNotFoundException;
import com.affaince.subscription.subscriber.web.request.AddressRequest;
import com.affaince.subscription.subscriber.web.request.BasketItemRequest;
import com.affaince.subscription.subscriber.web.request.ContactDetailsRequest;
import com.affaince.subscription.subscriber.web.request.SubscriptionRequest;
import com.google.common.collect.ImmutableMap;
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
    private final SubscriptionViewRepository subscriptionViewRepository;
    private final ProductViewRepository productViewRepository;

    @Autowired
    public SubscriptionController(SubscriptionCommandGateway commandGateway, SubscriptionViewRepository subscriptionViewRepository, ProductViewRepository productViewRepository) {
        this.commandGateway = commandGateway;
        this.subscriptionViewRepository = subscriptionViewRepository;
        this.productViewRepository = productViewRepository;
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
        return new ResponseEntity<Object>(ImmutableMap.of("id",subscriptionId),HttpStatus.CREATED);
    }

    @RequestMapping(value = "additem/{subscriptionId}", method = RequestMethod.PUT)
    @Consumes("application/json")
    public ResponseEntity<Object> addItemToConsumerBasket(@PathVariable String subscriptionId,
                                                          @RequestBody @Valid BasketItemRequest request) throws Exception {
        final SubscriptionView subscriptionView = subscriptionViewRepository.findOne(subscriptionId);
        final ProductView productView = productViewRepository.findOne(request.getProductId());
        final long productQuantity = productView.getNetQuantity();
        final QuantityUnit productQuantityUnit = productView.getQuantityUnit();
        double productQuantityInGrms = productQuantity;
        if (productQuantityUnit == QuantityUnit.KG || productQuantityUnit == QuantityUnit.LT) {
            productQuantityInGrms = productQuantity * 1000;
        } else if (productQuantityUnit == QuantityUnit.ml) {
            productQuantityInGrms = productQuantity;
        }
        final AddItemToSubscriptionCommand command = new AddItemToSubscriptionCommand(subscriptionId,
                request.getProductId(), request.getCountPerPeriod(), request.getPeriod(), request.getDiscountedOfferedPrice(),
                request.getOfferedPriceWithBasketLevelDiscount(), request.getNoOfCycles(), productQuantityInGrms);
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
        final SubscriptionView subscriptionView = subscriptionViewRepository.findOne(subscriptionId);
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
        final SubscriptionView subscriptionView = subscriptionViewRepository.findOne(subscriptionId);
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
        final SubscriptionView subscriptionView = subscriptionViewRepository.findOne(subscriptionId);
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
    public ResponseEntity<Object> deleteItem(@PathVariable String subscriptionId, @PathVariable String itemId) throws Exception {
        final SubscriptionView subscriptionView = subscriptionViewRepository.findOne(subscriptionId);
        if (subscriptionView == null) {
            throw ConsumerBasketNotFoundException.build(subscriptionId);
        }
        final DeleteItemFromSubscriptionCommand command = new DeleteItemFromSubscriptionCommand(subscriptionId, itemId);
        try {
            commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(value = "confirmsubscription/{subscriptionId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> confirmSubscription(@PathVariable String subscriptionId) throws Exception {
        final SubscriptionView subscriptionView = subscriptionViewRepository.findOne(subscriptionId);
        if (subscriptionView == null) {
            throw ConsumerBasketNotFoundException.build(subscriptionId);
        }
        final ConfirmSubscriptionCommand confirmSubscriptionCommand = new ConfirmSubscriptionCommand(subscriptionId);
        try {
            commandGateway.executeAsync(confirmSubscriptionCommand);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.OK);

    }
}
