package com.affaince.subscription.subscriber.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.subscriber.command.AddDeliveryToSubscriptionCommand;
import com.affaince.subscription.subscriber.command.DeleteDeliveryCommand;
import com.affaince.subscription.subscriber.command.UpdateDeliveryCommand;
import com.affaince.subscription.subscriber.web.request.AddDeliveryRequest;
import com.affaince.subscription.subscriber.web.request.UpdateDeliveryRequest;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import java.util.Arrays;

/**
 * Created by rbsavaliya on 23-10-2016.
 */
@RestController
@RequestMapping(value = "delivery")
public class DeliveryController {

    private final SubscriptionCommandGateway commandGateway;

    @Autowired
    public DeliveryController(SubscriptionCommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @RequestMapping(value = "add/{subscriberId}", method = RequestMethod.POST)
    @Consumes("application/json")
    public ResponseEntity<Object> addDelivery(@PathVariable String subscriberId, AddDeliveryRequest request) throws Exception {
        final String deliveryId = request.getDeliveryDate().getWeekOfWeekyear() + request.getDeliveryDate().getYear() + "";
        final AddDeliveryToSubscriptionCommand command = new AddDeliveryToSubscriptionCommand(
                subscriberId, deliveryId, request.getDeliveryDate(), Arrays.asList(request.getDeliveryItems())
        );
        commandGateway.executeAsync(command);
        return new ResponseEntity<Object>(ImmutableMap.of("id", deliveryId), HttpStatus.CREATED);
    }


    @RequestMapping(value = "delete/{subscriberId}/{deliveryId}", method = RequestMethod.DELETE)
    @Consumes("application/json")
    public ResponseEntity<Object> deleteDelivery(@PathVariable String subscriberId, @PathVariable String deliveryId) throws Exception {
        final DeleteDeliveryCommand command = new DeleteDeliveryCommand(subscriberId, deliveryId);
        commandGateway.executeAsync(command);
        return new ResponseEntity<Object>(ImmutableMap.of("id", deliveryId), HttpStatus.OK);
    }

    @RequestMapping(value = "update/{subscriberId}/{deliveryId}", method = RequestMethod.PUT)
    @Consumes("application/json")
    public ResponseEntity<Object> updateDelivery(@PathVariable String subscriberId, @PathVariable String deliveryId,
                                                 UpdateDeliveryRequest request) throws Exception {
        final UpdateDeliveryCommand command = new UpdateDeliveryCommand(subscriberId, deliveryId, request.getDeliveryDate(),
                Arrays.asList(request.getDeliveryItems()));
        commandGateway.executeAsync(command);
        return new ResponseEntity<Object>(ImmutableMap.of("id", deliveryId), HttpStatus.OK);
    }
}