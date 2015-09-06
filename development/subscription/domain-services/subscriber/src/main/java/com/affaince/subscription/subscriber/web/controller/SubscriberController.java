package com.affaince.subscription.subscriber.web.controller;

import com.affaince.subscription.common.vo.SubscriberName;
import com.affaince.subscription.subscriber.command.AddRewardPointsCommand;
import com.affaince.subscription.subscriber.command.CreateSubscriberCommand;
import com.affaince.subscription.subscriber.command.UpdateSubscriberAddressCommand;
import com.affaince.subscription.subscriber.command.UpdateSubscriberContactDetailsCommand;
import com.affaince.subscription.subscriber.web.request.AddRewardPointsRequest;
import com.affaince.subscription.subscriber.web.request.CreateSubscriberRequest;
import com.affaince.subscription.subscriber.web.request.UpdateSubscriberAddressRequest;
import com.affaince.subscription.subscriber.web.request.UpdateSubscriberContactDetailsRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import java.util.UUID;

/**
 * Created by rbsavaliya on 02-08-2015.
 */
@RestController
@RequestMapping(value = "subscriber")
public class SubscriberController {

    private final CommandGateway commandGateway;

    @Autowired
    public SubscriberController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @RequestMapping(method = RequestMethod.POST)
    @Consumes("application/json")
    public ResponseEntity<Object> createSubscriber(@RequestBody CreateSubscriberRequest request) {
        final CreateSubscriberCommand command = new CreateSubscriberCommand(UUID.randomUUID().toString(),
                request.getSubscriberName(), request.getBillingAddress(), request.getShippingAddress(),
                request.getContactDetails()
        );
        commandGateway.sendAndWait(command);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updatecontactdetails/{subscriberid}")
    @Consumes("application/json")
    public ResponseEntity<Object> updateSubscriberContactDetails(@RequestBody UpdateSubscriberContactDetailsRequest request,
                                                                 @PathVariable("subscriberid") String subscriberId) {
        final UpdateSubscriberContactDetailsCommand command = new UpdateSubscriberContactDetailsCommand(
                subscriberId, request.getEmail(), request.getMobileNumber(), request.getAlternativeNumber()
        );
        commandGateway.sendAndWait(command);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateaddress/{subscriberid}")
    @Consumes("application/json")
    public ResponseEntity<Object> updateSubscriberAddress(@RequestBody UpdateSubscriberAddressRequest request,
                                                          @PathVariable("subscriberid") String subscriberId) {
        final UpdateSubscriberAddressCommand command = new UpdateSubscriberAddressCommand(
                subscriberId, request.getAddressLine1(), request.getAddressLine2(), request.getCity(),
                request.getState(), request.getCountry(), request.getPinCode()
        );
        commandGateway.sendAndWait(command);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping (method = RequestMethod.PUT, value = "addrewardpoins/{subscriberid}")
    @Consumes ("application/json")
    public ResponseEntity <Object> addRewardPoints (@RequestBody AddRewardPointsRequest request,
                                                    @PathVariable ("subscriberid") String subscriberId) {

        final AddRewardPointsCommand command = new AddRewardPointsCommand (subscriberId, request.getRewardPoints());
        commandGateway.sendAndWait(command);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
