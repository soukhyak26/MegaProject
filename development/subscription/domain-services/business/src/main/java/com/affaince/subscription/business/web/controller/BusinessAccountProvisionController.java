package com.affaince.subscription.business.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.business.command.CreateProvisionCommand;
import com.affaince.subscription.business.query.repository.BusinessAccountViewRepository;
import com.affaince.subscription.business.web.request.ProvisionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Consumes;

/**
 * Created by anayonkar on 29/4/16.
 */
@RestController
@RequestMapping(value="businessacount")
public class BusinessAccountProvisionController {
    private final SubscriptionCommandGateway commandGateway;
    private final BusinessAccountViewRepository businessAccountViewRepository;

    @Autowired
    public BusinessAccountProvisionController(SubscriptionCommandGateway commandGateway, BusinessAccountViewRepository businessAccountViewRepository) {
        this.commandGateway = commandGateway;
        this.businessAccountViewRepository = businessAccountViewRepository;
    }

    @RequestMapping(method = RequestMethod.POST, value = "setProvision")
    @Consumes("application/json")
    public ResponseEntity<Object> setProvision(@RequestBody @Valid ProvisionRequest request) throws Exception {
        CreateProvisionCommand command = new CreateProvisionCommand(request.getProvisionForPurchaseCost(), request.getProvisionForLosses(), request.getProvisionDate());
        commandGateway.executeAsync(command);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
