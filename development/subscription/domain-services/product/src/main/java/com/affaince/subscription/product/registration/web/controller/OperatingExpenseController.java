package com.affaince.subscription.product.registration.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.product.registration.command.AddCommonOperatingExpenseCommand;
import com.affaince.subscription.product.registration.query.repository.DeliveryChargesRuleViewRepository;
import com.affaince.subscription.product.registration.query.view.DeliveryChargesRuleView;
import com.affaince.subscription.product.registration.vo.OperatingExpenseVO;
import com.affaince.subscription.product.registration.vo.RangeRule;
import com.affaince.subscription.product.registration.web.request.CommonOperatingExpensesRequest;
import com.affaince.subscription.product.registration.web.request.DeliveryChargesRulesRequest;
import org.jgroups.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.ws.rs.Consumes;

/**
 * Created by rbsavaliya on 16-01-2016.
 */
@RestController
@RequestMapping(value = "operatingexpense")
public class OperatingExpenseController {

    private final SubscriptionCommandGateway commandGateway;
    private final DeliveryChargesRuleViewRepository deliveryChargesRuleViewRepository;

    @Autowired
    public OperatingExpenseController(SubscriptionCommandGateway commandGateway, DeliveryChargesRuleViewRepository deliveryChargesRuleViewRepository) {
        this.commandGateway = commandGateway;
        this.deliveryChargesRuleViewRepository = deliveryChargesRuleViewRepository;
    }

    @RequestMapping(method = RequestMethod.POST, value = "common")
    @Consumes("application/json")
    public ResponseEntity<Object> setOperatingExpenses(@RequestBody @Valid CommonOperatingExpensesRequest request) throws Exception {
        for (OperatingExpenseVO expense : request.getExpenses()) {
            final AddCommonOperatingExpenseCommand command = new AddCommonOperatingExpenseCommand(new UUID().randomUUID().toString(),
                    expense);
            try {
                commandGateway.executeAsync(command);
            } catch (Exception e) {
                throw e;
            }
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "deliverychargerules")
    @Consumes("application/json")
    public ResponseEntity<Object> setDeliveryChargesRules(@RequestBody @Valid DeliveryChargesRulesRequest request) throws Exception {

        for (RangeRule rangeRule : request.getDeliveryChargesRules()) {
            final DeliveryChargesRuleView deliveryChargesRuleView = new DeliveryChargesRuleView(java.util.UUID.randomUUID().toString(),
                    rangeRule.getRuleHeader(), rangeRule.getRuleMinimum(), rangeRule.getRuleMaximum(),
                    rangeRule.getRuleUnit(), rangeRule.getApplicableValue());
            deliveryChargesRuleViewRepository.save(deliveryChargesRuleView);
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}
