package com.affaince.subscription.subscriber.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.subscriber.command.AddDeliveryChargesRuleCommand;
import com.affaince.subscription.subscriber.query.repository.DeliveryChargesRuleViewRepository;
import com.affaince.subscription.subscriber.query.view.DeliveryChargesRuleView;
import com.affaince.subscription.subscriber.web.request.DeliveryChargesRulesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rsavaliya on 27/2/16.
 */
@RestController
@RequestMapping (value = "deliverychargerules")
public class DeliveryChargesRuleController {

    private final SubscriptionCommandGateway commandGateway;

    private final DeliveryChargesRuleViewRepository deliveryChargesRuleViewRepository;

    @Autowired
    public DeliveryChargesRuleController(SubscriptionCommandGateway commandGateway, DeliveryChargesRuleViewRepository deliveryChargesRuleViewRepository) {
        this.commandGateway = commandGateway;
        this.deliveryChargesRuleViewRepository = deliveryChargesRuleViewRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    @Consumes("application/json")
    public ResponseEntity<Object> setDeliveryChargesRules(@RequestBody @Valid DeliveryChargesRulesRequest request) throws Exception {

        final AddDeliveryChargesRuleCommand addDeliveryChargesRuleCommand = new
                AddDeliveryChargesRuleCommand (request.getRuleId(), Arrays.asList(request.getDeliveryChargesRules() ));
        commandGateway.send(addDeliveryChargesRuleCommand);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping (method = RequestMethod.GET, value = "/all")
    public ResponseEntity <List<DeliveryChargesRuleView>> findDeliveryChargesRules (){
        final List<DeliveryChargesRuleView> deliveryChargesRuleViews = new ArrayList<>();
        deliveryChargesRuleViewRepository.findAll().forEach(deliveryChargesRuleView -> deliveryChargesRuleViews.add(deliveryChargesRuleView));
        return new ResponseEntity<List<DeliveryChargesRuleView>>(deliveryChargesRuleViews, HttpStatus.OK);
    }
}
