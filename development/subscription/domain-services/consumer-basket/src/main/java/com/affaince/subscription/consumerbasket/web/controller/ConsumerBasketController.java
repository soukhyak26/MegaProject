package com.affaince.subscription.consumerbasket.web.controller;

import com.affaince.subscription.consumerbasket.command.AddAddressToConsumerBasketCommand;
import com.affaince.subscription.consumerbasket.command.AddContactDetailsCommand;
import com.affaince.subscription.consumerbasket.command.CreateConsumerBasketCommand;
import com.affaince.subscription.consumerbasket.web.request.AddressRequest;
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
       /* final ContactDetails contactDetails = request.getContactDetails();
        AddContactDetailsCommand contactDetailsCommand = new AddContactDetailsCommand(basketId,
                contactDetails.getEmail(), contactDetails.getMobileNumber(), contactDetails.getAlternativeNumber()
        );
        commandGateway.sendAndWait(contactDetailsCommand);
        final Address billingAddress = request.getBillingAddress();
        AddAddressToConsumerBasketCommand addBillingAddressCommand = new AddAddressToConsumerBasketCommand(
                basketId, "billing", billingAddress.getAddressLine1(), billingAddress.getAddressLine2(), billingAddress.getCity(),
                billingAddress.getState(), billingAddress.getCountry(), billingAddress.getPinCode()
        );
        commandGateway.sendAndWait(addBillingAddressCommand);
        final Address shippingAddress = request.getShippingAddress();
        AddAddressToConsumerBasketCommand addShippingAddressComand = new AddAddressToConsumerBasketCommand(
                basketId, "shipping", shippingAddress.getAddressLine1(), shippingAddress.getAddressLine2(), shippingAddress.getCity(),
                shippingAddress.getState(), shippingAddress.getCountry(), shippingAddress.getPinCode()
        );
        commandGateway.sendAndWait(addShippingAddressComand);*/
        return new ResponseEntity<Object>(HttpStatus.CREATED);
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
}
