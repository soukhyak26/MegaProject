package com.affaince.subscription.consumerbasket.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.consumerbasket.command.AddBasketRulesCommand;
import com.affaince.subscription.consumerbasket.query.repository.ConsumerBasketRepository;
import com.affaince.subscription.consumerbasket.web.request.BasketRulesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created by rbsavaliya on 26-09-2015.
 */
@RestController
@RequestMapping(value = "basketrules")
public class BasketRulesController {

    private final SubscriptionCommandGateway commandGateway;
    private final ConsumerBasketRepository repository;

    @Autowired
    public BasketRulesController(SubscriptionCommandGateway commandGateway, ConsumerBasketRepository repository) {
        this.commandGateway = commandGateway;
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> addBasketRules(@RequestBody BasketRulesRequest request) throws Exception {
        AddBasketRulesCommand command = new AddBasketRulesCommand(UUID.randomUUID().toString(),
                request.getMaximumPermissibleAmount(), request.getMinimumAmountForDiscountEligibility(),
                request.getMaximumPermissibleDiscount(), request.getMaximumPermissibleDiscountUnit());
        try {
            commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "update/{basketRuleId}")
    public ResponseEntity<Object> updateBasketRules(@RequestBody BasketRulesRequest request,
                                                    @PathVariable String basketRuleId) throws Exception {
        AddBasketRulesCommand command = new AddBasketRulesCommand(basketRuleId,
                request.getMaximumPermissibleAmount(), request.getMinimumAmountForDiscountEligibility(),
                request.getMaximumPermissibleDiscount(), request.getMaximumPermissibleDiscountUnit());
        try {
            commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}