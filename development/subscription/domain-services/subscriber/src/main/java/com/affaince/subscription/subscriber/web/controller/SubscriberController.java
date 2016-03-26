package com.affaince.subscription.subscriber.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.subscriber.command.*;
import com.affaince.subscription.subscriber.query.repository.SubscriberViewRepository;
import com.affaince.subscription.subscriber.query.repository.SubscriptionTemplateViewRepository;
import com.affaince.subscription.subscriber.query.view.SubscriberView;
import com.affaince.subscription.subscriber.query.view.SubscriptionTemplateView;
import com.affaince.subscription.subscriber.web.exception.SubscriberNotFoundException;
import com.affaince.subscription.subscriber.web.request.*;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by rbsavaliya on 02-08-2015.
 */
@RestController
@RequestMapping(value = "subscriber")
public class SubscriberController {

    private final SubscriptionCommandGateway commandGateway;
    private final SubscriberViewRepository subscriberViewRepository;
    private final SubscriptionTemplateViewRepository subscriptionTemplateViewRepository;

    @Autowired
    public SubscriberController(SubscriptionCommandGateway commandGateway, SubscriberViewRepository subscriberViewRepository, SubscriptionTemplateViewRepository subscriptionTemplateViewRepository) {
        this.commandGateway = commandGateway;
        this.subscriberViewRepository = subscriberViewRepository;
        this.subscriptionTemplateViewRepository = subscriptionTemplateViewRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    @Consumes("application/json")
    public ResponseEntity<Object> createSubscriber(@RequestBody @Valid CreateSubscriberRequest request) throws Exception {
        final String subscriberId = UUID.randomUUID().toString();
        final CreateSubscriberCommand command = new CreateSubscriberCommand(subscriberId,
                request.getSubscriberName(), request.getAddress(), request.getContactDetails()
        );
        try {
            commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(ImmutableMap.of("id", subscriberId), HttpStatus.CREATED);
    }

    @RequestMapping(value = "password/{subscriberId}", method = RequestMethod.PUT)
    @Consumes("application/json")
    public ResponseEntity<Object> createPassword(@PathVariable String subscriberId, @RequestBody String password) throws Exception {
        final SubscriberView subscriberView = subscriberViewRepository.findOne(subscriberId);
        if (subscriberView == null) {
            throw SubscriberNotFoundException.build(subscriberId);
        }
        final SetSubscriberPasswordCommand command = new SetSubscriberPasswordCommand(subscriberId, password);
        try {
            commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updatecontactdetails/{subscriberid}")
    @Consumes("application/json")
    public ResponseEntity<Object> updateSubscriberContactDetails(@RequestBody @Valid UpdateSubscriberContactDetailsRequest request,
                                                                 @PathVariable("subscriberid") String subscriberId) throws Exception {
        final SubscriberView subscriberView = subscriberViewRepository.findOne(subscriberId);
        if (subscriberView == null) {
            throw SubscriberNotFoundException.build(subscriberId);
        }
        final UpdateSubscriberContactDetailsCommand command = new UpdateSubscriberContactDetailsCommand(
                subscriberId, request.getEmail(), request.getMobileNumber(), request.getAlternativeNumber()
        );
        try {
            commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateaddress/{subscriberid}")
    @Consumes("application/json")
    public ResponseEntity<Object> updateSubscriberAddress(@RequestBody @Valid UpdateSubscriberAddressRequest request,
                                                          @PathVariable("subscriberid") String subscriberId) throws Exception {
        final SubscriberView subscriberView = subscriberViewRepository.findOne(subscriberId);
        if (subscriberView == null) {
            throw SubscriberNotFoundException.build(subscriberId);
        }
        final UpdateSubscriberAddressCommand command = new UpdateSubscriberAddressCommand(
                subscriberId, request.getAddressLine1(), request.getAddressLine2(), request.getCity(),
                request.getState(), request.getCountry(), request.getPinCode()
        );
        try {
            commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "addrewardpoins/{subscriberid}")
    @Consumes("application/json")
    public ResponseEntity<Object> addRewardPoints(@RequestBody @Valid AddRewardPointsRequest request,
                                                  @PathVariable("subscriberid") String subscriberId) throws Exception {
        final SubscriberView subscriberView = subscriberViewRepository.findOne(subscriberId);
        if (subscriberView == null) {
            throw SubscriberNotFoundException.build(subscriberId);
        }
        final AddRewardPointsCommand command = new AddRewardPointsCommand(subscriberId, request.getRewardPoints());
        try {
            commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "addcouponcode/{subscriberid}")
    @Consumes("application/json")
    public ResponseEntity<Object> addRewardPoints(@RequestBody @Valid AddCoupanCodeRequest request,
                                                  @PathVariable("subscriberid") String subscriberId) throws Exception {

        final SubscriberView subscriberView = subscriberViewRepository.findOne(subscriberId);
        if (subscriberView == null) {
            throw SubscriberNotFoundException.build(subscriberId);
        }
        final AddCouponCodeCommand command = new AddCouponCodeCommand(subscriberId, request.getCouponCode());
        try {
            commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "template")
    @Consumes("application/json")
    public ResponseEntity <Object> addSubscriptionTemplate (@RequestBody AddSubscriptionTemplateRequest request) {
        final SubscriptionTemplateView subscriptionTemplateView = new SubscriptionTemplateView(
                UUID.randomUUID().toString(),
                Arrays.asList(request.getSubscriptionRequirementOfOneFamily())
        );
        subscriptionTemplateViewRepository.save(subscriptionTemplateView);
        return new ResponseEntity<Object>(subscriptionTemplateView, HttpStatus.OK);
    }
}
