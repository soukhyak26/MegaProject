package com.affaince.subscription.subscriber.web.controller;

import com.affaince.subscription.common.type.DeliveryChargesRuleType;
import com.affaince.subscription.common.vo.EntityMetricType;
import com.affaince.subscription.common.vo.EntityType;
import com.affaince.subscription.subscriber.query.predictions.DeliveryHistoryRetriever;
import com.affaince.subscription.subscriber.query.predictions.SubscribersHistoryRetriever;
import com.affaince.subscription.subscriber.query.predictions.SubscriptionsHistoryRetriever;
import com.affaince.subscription.subscriber.query.repository.DeliveryChargesRuleViewRepository;
import com.affaince.subscription.subscriber.query.repository.SubscriptionRuleViewRepository;
import com.affaince.subscription.subscriber.query.view.DeliveryChargesRuleView;
import com.affaince.subscription.subscriber.query.view.SubscriptionRuleView;
import com.affaince.subscription.subscriber.vo.RangeRule;
import com.affaince.subscription.subscriber.vo.SubscriptionValueRange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mandar on 9/1/2017.
 */
@RestController
@RequestMapping(value = "/subscriber/forecast")
@Component

public class ForecastController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ForecastController.class);
    private final SubscriptionsHistoryRetriever subscriptionsHistoryRetriever;
    private final SubscribersHistoryRetriever subscribersHistoryRetriever;
    private final DeliveryHistoryRetriever deliveryHistoryRetriever;
    private final DeliveryChargesRuleViewRepository deliveryChargesRuleViewRepository;
    private final SubscriptionRuleViewRepository subscriptionRuleViewRepository;
    @Autowired
    public ForecastController(SubscriptionsHistoryRetriever subscriptionsHistoryRetriever, SubscribersHistoryRetriever subscribersHistoryRetriever,DeliveryHistoryRetriever deliveryHistoryRetriever,DeliveryChargesRuleViewRepository deliveryChargesRuleViewRepository,SubscriptionRuleViewRepository subscriptionRuleViewRepository) {
        this.subscriptionsHistoryRetriever = subscriptionsHistoryRetriever;
        this.subscribersHistoryRetriever = subscribersHistoryRetriever;
        this.deliveryHistoryRetriever=deliveryHistoryRetriever;
        this.deliveryChargesRuleViewRepository= deliveryChargesRuleViewRepository;
        this.subscriptionRuleViewRepository=subscriptionRuleViewRepository;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/predict/subscription")
    public ResponseEntity<String> forecastSubscriptionDemand() throws Exception {
        //TODO: Assumption is .. there is a single Subscription RuleView object at any time.. to validate if this is true
        SubscriptionRuleView subscriptionRuleView = subscriptionRuleViewRepository.findAll().iterator().next();
        List<SubscriptionValueRange>  valueRanges= subscriptionRuleView.getSubscriptionValueRanges();
        for(SubscriptionValueRange valueRange: valueRanges) {
            Map<String, Object> metadata = new HashMap<>();
            metadata.put("ENTITY_TYPE", EntityType.SUBSCRIPTION);
            metadata.put("ENTITY_METRIC_TYPE", EntityMetricType.TOTAL);
            metadata.put("MIN_WEIGHT", valueRange.getMinimumValue());
            metadata.put("MAX_WEIGHT", valueRange.getMaximumValue());
            subscriptionsHistoryRetriever.marshallSendAndReceive(null, metadata);
        }
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/predict/subscriber")
    public ResponseEntity<String> forecastSubscriberDemand() throws Exception {
        Map<String,Object> metadata= new HashMap<>();
        metadata.put("ENTITY_TYPE", EntityType.SUBSCRIBER);
        metadata.put("ENTITY_METRIC_TYPE", EntityMetricType.TOTAL);
        subscribersHistoryRetriever.marshallSendAndReceive(null,metadata);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/predict/deliveries")
    public ResponseEntity<String> forecastDeliveryDemand() throws Exception {
        //List<DeliveriesWeightRangeConfig.WeightRange> weightRanges=deliveriesWeightRangeConfig.getWeightRange();
        DeliveryChargesRuleView deliverychargesRuleView= deliveryChargesRuleViewRepository.findFirstByRuleIdOrderByEffectiveDateDesc(DeliveryChargesRuleType.CHARGES_ON_DELIVERY_WEIGHT);
        List<RangeRule> weightRanges= deliverychargesRuleView.getRangeRules();
        for (RangeRule rule: weightRanges) {
            Map<String,Object> metadata= new HashMap<>();
            metadata.put("ENTITY_TYPE", EntityType.DELIVERY);
            metadata.put("ENTITY_METRIC_TYPE", EntityMetricType.TOTAL);
            metadata.put("MIN_WEIGHT", rule.getRuleMinimum());
            metadata.put("MAX_WEIGHT",rule.getRuleMaximum());
            deliveryHistoryRetriever.marshallSendAndReceive(null,metadata);
        }
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

}
