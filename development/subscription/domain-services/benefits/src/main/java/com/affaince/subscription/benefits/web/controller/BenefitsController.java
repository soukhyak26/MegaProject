package com.affaince.subscription.benefits.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.benefits.command.AddBenefitCommand;
import com.affaince.subscription.benefits.web.request.BenefitRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by rbsavaliya on 25-10-2015.
 */
@RestController(value = "benefits")
public class BenefitsController {

    private final SubscriptionCommandGateway commandGateway;

    @Autowired
    public BenefitsController(SubscriptionCommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> addBenefit(@RequestBody BenefitRequest request) throws Exception {
        final AddBenefitCommand command = new AddBenefitCommand(UUID.randomUUID().toString(),
                request.getBenefitEquation(), request.getActivationStartTime(), request.getActivationEndTime());
        commandGateway.executeAsync(command);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
