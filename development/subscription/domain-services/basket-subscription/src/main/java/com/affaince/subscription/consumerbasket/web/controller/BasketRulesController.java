package com.affaince.subscription.consumerbasket.web.controller;

import com.affaince.subscription.consumerbasket.command.AddBasketRulesCommand;
import com.affaince.subscription.consumerbasket.query.repository.ConsumerBasketRepository;
import com.affaince.subscription.consumerbasket.web.request.BasketRulesRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by rbsavaliya on 26-09-2015.
 */
@RestController
@RequestMapping(value = "basketrules")
public class BasketRulesController {

    private final CommandGateway commandGateway;
    private final ConsumerBasketRepository repository;

    @Autowired
    public BasketRulesController(CommandGateway commandGateway, ConsumerBasketRepository repository) {
        this.commandGateway = commandGateway;
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> updateBasketRules(@RequestBody BasketRulesRequest request) {
        AddBasketRulesCommand command = new AddBasketRulesCommand(UUID.randomUUID().toString(),
                request.getMaximumPermissibleAmount(), request.getMinimumAmountForDiscountEligibility(),
                request.getMaximumPermissibleDiscount(), request.getMaximumPermissibleDiscountUnit());
        commandGateway.sendAndWait(command);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
