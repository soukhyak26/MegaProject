package com.affaince.subscription.subscriber.web.controller;

import com.affaince.subscription.subscriber.command.AddDeliveryChargesRuleCommand;
import com.affaince.subscription.subscriber.vo.RangeRule;
import com.affaince.subscription.subscriber.web.request.DeliveryChargesRulesRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.ws.rs.Consumes;

/**
 * Created by rsavaliya on 27/2/16.
 */
@RestController
@RequestMapping (value = "deliverychargerules")
public class DeliveryChargesRuleController {

    @RequestMapping(method = RequestMethod.POST, value = "deliverychargerules")
    @Consumes("application/json")
    public ResponseEntity<Object> setDeliveryChargesRules(@RequestBody @Valid DeliveryChargesRulesRequest request) throws Exception {

        for (RangeRule rangeRule : request.getDeliveryChargesRules()) {
            final AddDeliveryChargesRuleCommand addDeliveryChargesRuleCommand = new
                    AddDeliveryChargesRuleCommand (request.getRuleId(), request.getDeliveryChargesRules());
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
