package com.affaince.subscription.subscriber.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.subscriber.command.AddBasketRulesCommand;
import com.affaince.subscription.subscriber.query.repository.SubscriptionViewRepository;
import com.affaince.subscription.subscriber.web.request.BasketRulesRequest;
import com.google.common.collect.ImmutableMap;
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
    private final SubscriptionViewRepository repository;

    @Autowired
    public BasketRulesController(SubscriptionCommandGateway commandGateway, SubscriptionViewRepository repository) {
        this.commandGateway = commandGateway;
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> addBasketRules(@RequestBody BasketRulesRequest request) throws Exception {
        final String basketRulesId = UUID.randomUUID().toString();
        final AddBasketRulesCommand command = new AddBasketRulesCommand(basketRulesId,
                request.getMaximumPermissibleAmount(), request.getMinimumAmountForDiscountEligibility(),
                request.getMaximumPermissibleDiscount(), request.getMaximumPermissibleDiscountUnit(),
                request.getMinimumAmountEligibleForFreeShipping());
        try {
            commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(ImmutableMap.of("id",basketRulesId),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "update/{basketRuleId}")
    public ResponseEntity<Object> updateBasketRules(@RequestBody BasketRulesRequest request,
                                                    @PathVariable String basketRuleId) throws Exception {
        final AddBasketRulesCommand command = new AddBasketRulesCommand(basketRuleId,
                request.getMaximumPermissibleAmount(), request.getMinimumAmountForDiscountEligibility(),
                request.getMaximumPermissibleDiscount(), request.getMaximumPermissibleDiscountUnit(),
                request.getMinimumAmountEligibleForFreeShipping());
        try {
            commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
