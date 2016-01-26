package com.affaince.subscription.business.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.business.command.AddCommonOperatingExpenseCommand;
import com.affaince.subscription.business.query.repository.CommonOperatingExpenseViewRepository;
import com.affaince.subscription.business.query.repository.DeliveryChargesRuleViewRepository;
import com.affaince.subscription.business.query.view.DeliveryChargesRuleView;
import com.affaince.subscription.business.vo.OperatingExpenseVO;
import com.affaince.subscription.business.vo.RangeRule;
import com.affaince.subscription.business.web.request.CommonOperatingExpensesRequest;
import com.affaince.subscription.business.web.request.DeliveryChargesRulesRequest;
import com.affaince.subscription.common.type.PeriodUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import java.util.UUID;

/**
 * Created by rbsavaliya on 16-01-2016.
 */
@RestController
@RequestMapping(value = "operatingexpense")
public class OperatingExpenseController {

    private final SubscriptionCommandGateway commandGateway;
    private final DeliveryChargesRuleViewRepository deliveryChargesRuleViewRepository;
    private final CommonOperatingExpenseViewRepository commonOperatingExpenseViewRepository;

    @Autowired
    public OperatingExpenseController(SubscriptionCommandGateway commandGateway, DeliveryChargesRuleViewRepository deliveryChargesRuleViewRepository, CommonOperatingExpenseViewRepository commonOperatingExpenseViewRepository) {
        this.commandGateway = commandGateway;
        this.deliveryChargesRuleViewRepository = deliveryChargesRuleViewRepository;
        this.commonOperatingExpenseViewRepository = commonOperatingExpenseViewRepository;
    }

    @RequestMapping(method = RequestMethod.POST, value = "common")
    @Consumes("application/json")
    public ResponseEntity<Object> setOperatingExpenses(@RequestBody @Valid CommonOperatingExpensesRequest request) throws Exception {
        String commonExpenseId = UUID.randomUUID().toString();
        double amount = 0;
        for (OperatingExpenseVO expense : request.getExpenses()) {
            if (expense.getPeriod().getUnit() == PeriodUnit.WEEK) {
                amount = (expense.getAmount() / expense.getPeriod().getValue()) * 4;
            } else if (expense.getPeriod().getUnit() == PeriodUnit.MONTH) {
                amount = (expense.getAmount() / expense.getPeriod().getValue());
            } else if (expense.getPeriod().getUnit() == PeriodUnit.YEAR) {
                amount = expense.getAmount() / (expense.getPeriod().getValue() * 12);
            }

            final AddCommonOperatingExpenseCommand command = new AddCommonOperatingExpenseCommand(commonExpenseId, expense.getExpenseHeader(), amount);
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
