package com.affaince.subscription.subscriber.web.controller;

import com.affaince.subscription.subscriber.command.*;
import com.affaince.subscription.subscriber.query.repository.SubscriberViewRepository;
import com.affaince.subscription.subscriber.query.view.SubscriberView;
import com.affaince.subscription.subscriber.web.exception.SubscriberNotFoundException;
import com.affaince.subscription.subscriber.web.request.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import java.util.UUID;

/**
 * Created by rbsavaliya on 02-08-2015.
 */
@RestController
@RequestMapping(value = "subscriber")
public class SubscriberController {

    private final CommandGateway commandGateway;
    private final SubscriberViewRepository repository;

    @Autowired
    public SubscriberController(CommandGateway commandGateway, SubscriberViewRepository repository) {
        this.commandGateway = commandGateway;
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.POST)
    @Consumes("application/json")
    public ResponseEntity<Object> createSubscriber(@RequestBody @Valid CreateSubscriberRequest request) {
        final CreateSubscriberCommand command = new CreateSubscriberCommand(UUID.randomUUID().toString(),
                request.getSubscriberName(), request.getBillingAddress(), request.getShippingAddress(),
                request.getContactDetails()
        );
        commandGateway.sendAndWait(command);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updatecontactdetails/{subscriberid}")
    @Consumes("application/json")
    public ResponseEntity<Object> updateSubscriberContactDetails(@RequestBody @Valid UpdateSubscriberContactDetailsRequest request,
                                                                 @PathVariable("subscriberid") String subscriberId) throws SubscriberNotFoundException {
        final SubscriberView subscriberView = repository.findOne(subscriberId);
        if (subscriberView == null) {
            throw SubscriberNotFoundException.build(subscriberId);
        }
        final UpdateSubscriberContactDetailsCommand command = new UpdateSubscriberContactDetailsCommand(
                subscriberId, request.getEmail(), request.getMobileNumber(), request.getAlternativeNumber()
        );
        commandGateway.sendAndWait(command);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateaddress/{subscriberid}")
    @Consumes("application/json")
    public ResponseEntity<Object> updateSubscriberAddress(@RequestBody @Valid UpdateSubscriberAddressRequest request,
                                                          @PathVariable("subscriberid") String subscriberId) throws SubscriberNotFoundException {
        final SubscriberView subscriberView = repository.findOne(subscriberId);
        if (subscriberView == null) {
            throw SubscriberNotFoundException.build(subscriberId);
        }
        final UpdateSubscriberAddressCommand command = new UpdateSubscriberAddressCommand(
                subscriberId, request.getAddressLine1(), request.getAddressLine2(), request.getCity(),
                request.getState(), request.getCountry(), request.getPinCode()
        );
        commandGateway.sendAndWait(command);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "addrewardpoins/{subscriberid}")
    @Consumes("application/json")
    public ResponseEntity<Object> addRewardPoints(@RequestBody @Valid AddRewardPointsRequest request,
                                                  @PathVariable("subscriberid") String subscriberId) throws SubscriberNotFoundException {
        final SubscriberView subscriberView = repository.findOne(subscriberId);
        if (subscriberView == null) {
            throw SubscriberNotFoundException.build(subscriberId);
        }
        final AddRewardPointsCommand command = new AddRewardPointsCommand(subscriberId, request.getRewardPoints());
        commandGateway.sendAndWait(command);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "addcouponcode/{subscriberid}")
    @Consumes("application/json")
    public ResponseEntity<Object> addRewardPoints(@RequestBody @Valid AddCoupanCodeRequest request,
                                                  @PathVariable("subscriberid") String subscriberId) throws SubscriberNotFoundException {

        final SubscriberView subscriberView = repository.findOne(subscriberId);
        if (subscriberView == null) {
            throw SubscriberNotFoundException.build(subscriberId);
        }
        final AddCouponCodeCommand command = new AddCouponCodeCommand(subscriberId, request.getCouponCode());
        commandGateway.sendAndWait(command);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
