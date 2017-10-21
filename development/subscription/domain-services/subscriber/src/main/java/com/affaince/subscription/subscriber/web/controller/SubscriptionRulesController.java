package com.affaince.subscription.subscriber.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.subscriber.command.AddSubscriptionRulesCommand;
import com.affaince.subscription.subscriber.query.repository.SubscriptionViewRepository;
import com.affaince.subscription.subscriber.web.request.SubscriptionRulesRequest;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created by rbsavaliya on 26-09-2015.
 */
@RestController
@RequestMapping(value = "subscriptionrules")
public class SubscriptionRulesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionRulesController.class);
    private final SubscriptionCommandGateway commandGateway;
    private final SubscriptionViewRepository repository;

    @Autowired
    public SubscriptionRulesController(SubscriptionCommandGateway commandGateway, SubscriptionViewRepository repository) {
        this.commandGateway = commandGateway;
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> addSubscriptionRules(@RequestBody SubscriptionRulesRequest request) throws Exception {
        final String subscriptionRulesId = UUID.randomUUID().toString();
        final AddSubscriptionRulesCommand command = new AddSubscriptionRulesCommand(subscriptionRulesId,
                request.getMaximumPermissibleAmount(), request.getMinimumAmountForDiscountEligibility(),
                request.getMaximumPermissibleDiscount(), request.getMaximumPermissibleDiscountUnit(),
                request.getMinimumAmountEligibleForFreeShipping(), request.getDiffBetweenDeliveryPreparationAndDispatchDate(),request.getActualsAggregationPeriodForTargetForecast(),request.getContingencyStockPercentage(),request.getSubscriptionValueRanges());
        try {
            commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        SubscriptionRulesController.LOGGER.info("Rules for subscription are defined:" + subscriptionRulesId +"on date:" + SysDate.now());
        return new ResponseEntity<Object>(ImmutableMap.of("id", subscriptionRulesId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "update/{basketRuleId}")
    public ResponseEntity<Object> updateSubscriptionRules(@RequestBody SubscriptionRulesRequest request,
                                                          @PathVariable String basketRuleId) throws Exception {
        final AddSubscriptionRulesCommand command = new AddSubscriptionRulesCommand(basketRuleId,
                request.getMaximumPermissibleAmount(), request.getMinimumAmountForDiscountEligibility(),
                request.getMaximumPermissibleDiscount(), request.getMaximumPermissibleDiscountUnit(),
                request.getMinimumAmountEligibleForFreeShipping(), request.getDiffBetweenDeliveryPreparationAndDispatchDate(),request.getActualsAggregationPeriodForTargetForecast(),request.getContingencyStockPercentage(),request.getSubscriptionValueRanges());
        try {
            commandGateway.executeAsync(command);
        } catch (Exception e) {
            throw e;
        }
        SubscriptionRulesController.LOGGER.info("Rules for subscription are updated:" + basketRuleId +"on date:" + SysDate.now());
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
