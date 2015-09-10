package com.affaince.subscription.consumerbasket.web.controller;

import com.affaince.subscription.consumerbasket.command.*;
import com.affaince.subscription.consumerbasket.web.request.AddressRequest;
import com.affaince.subscription.consumerbasket.web.request.BasketItemRequest;
import com.affaince.subscription.consumerbasket.web.request.ConsumerBasketRequest;
import com.affaince.subscription.consumerbasket.web.request.ContactDetailsRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import java.util.UUID;

/**
 * Created by rbsavaliya on 09-08-2015.
 */
@RestController
@RequestMapping(value = "consumerbasket")
public class ConsumerBasketController {

    private final CommandGateway commandGateway;

    @Autowired
    public ConsumerBasketController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @RequestMapping(method = RequestMethod.POST)
    @Consumes("application/json")
    public ResponseEntity<Object> createConsumerBasket(@RequestBody ConsumerBasketRequest request) {
        final String basketId = UUID.randomUUID().toString();
        CreateConsumerBasketCommand command = new CreateConsumerBasketCommand(basketId, request.getUserId());
        commandGateway.sendAndWait(command);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "additem/{basketid}", method = RequestMethod.PUT)
    @Consumes("application/json")
    public ResponseEntity<Object> addItemToConsumerBasket(@PathVariable("basketid") String basketId,
                                                          @RequestBody BasketItemRequest request) {
        AddItemToConsumerBasketCommand command = new AddItemToConsumerBasketCommand(basketId,
                request.getItemId(), request.getQuantityPerBasket(),
                request.getFrequency(), request.getFrequencyUnit(),
                request.getDiscountedOfferedPrice());
        commandGateway.sendAndWait(command);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(value = "addshippingaddress/{basketid}", method = RequestMethod.PUT)
    @Consumes("application/json")
    public ResponseEntity<Object> addShippingAddress(@PathVariable("basketid") String basketId,
                                                     @RequestBody AddressRequest request) {
        AddAddressToConsumerBasketCommand addShippingAddressCommand = new AddAddressToConsumerBasketCommand(
                basketId, "shipping", request.getAddressLine1(), request.getAddressLine2(), request.getCity(),
                request.getState(), request.getCountry(), request.getPinCode()
        );
        commandGateway.sendAndWait(addShippingAddressCommand);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(value = "addbillingaddress/{basketid}", method = RequestMethod.PUT)
    @Consumes("application/json")
    public ResponseEntity<Object> addBillingAddress(@PathVariable("basketid") String basketId,
                                                    @RequestBody AddressRequest request) {
        AddAddressToConsumerBasketCommand addBillingAddressCommand = new AddAddressToConsumerBasketCommand(
                basketId, "billing", request.getAddressLine1(), request.getAddressLine2(), request.getCity(),
                request.getState(), request.getCountry(), request.getPinCode()
        );
        commandGateway.sendAndWait(addBillingAddressCommand);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(value = "addcontactdetails/{basketid}", method = RequestMethod.PUT)
    @Consumes("application/json")
    public ResponseEntity<Object> addContactDetails(@PathVariable("basketid") String basketId,
                                                    @RequestBody ContactDetailsRequest request) {
        AddContactDetailsCommand contactDetailsCommand = new AddContactDetailsCommand(basketId,
                request.getEmail(), request.getMobileNumber(), request.getAlternativeNumber()
        );
        commandGateway.sendAndWait(contactDetailsCommand);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(value = "deleteitem/{basketId}/{itemId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteItem(@PathVariable String basketId, @PathVariable String itemId) {
        final DeleteItemCommand command = new DeleteItemCommand(basketId, itemId);
        commandGateway.sendAndWait(command);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
