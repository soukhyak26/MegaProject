package com.affaince.subscription.subscriber.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.common.type.DeliveryChargesRuleType;
import com.affaince.subscription.common.vo.EntityMetricType;
import com.affaince.subscription.common.vo.EntityType;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.subscriber.command.UpdateDeliveryForecastFromActualsCommand;
import com.affaince.subscription.subscriber.command.UpdateSubscriberForecastFromActualsCommand;
import com.affaince.subscription.subscriber.command.UpdateSubscriptionForecastFromActualsCommand;
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
import org.springframework.web.bind.annotation.PathVariable;
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
    private final SubscriptionCommandGateway commandGateway;
    @Autowired
    public ForecastController(SubscriptionsHistoryRetriever subscriptionsHistoryRetriever, SubscribersHistoryRetriever subscribersHistoryRetriever,DeliveryHistoryRetriever deliveryHistoryRetriever,DeliveryChargesRuleViewRepository deliveryChargesRuleViewRepository,SubscriptionRuleViewRepository subscriptionRuleViewRepository,SubscriptionCommandGateway commandGateway) {
        this.subscriptionsHistoryRetriever = subscriptionsHistoryRetriever;
        this.subscribersHistoryRetriever = subscribersHistoryRetriever;
        this.deliveryHistoryRetriever=deliveryHistoryRetriever;
        this.deliveryChargesRuleViewRepository= deliveryChargesRuleViewRepository;
        this.subscriptionRuleViewRepository=subscriptionRuleViewRepository;
        this.commandGateway=commandGateway;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/predict/subscription/{metrictype}")
    public ResponseEntity<String> forecastSubscriptionDemand(@PathVariable("metrictype") String metricType ) throws Exception {
        final int subscriptionAnalyserId=1;
        EntityMetricType entityMetricType=null;
        switch(metricType){
            case "new":
                entityMetricType=EntityMetricType.NEW;
                break;
            case "churn":
                entityMetricType=EntityMetricType.CHURN;
                break;
            case "total":
                entityMetricType=EntityMetricType.TOTAL;
                break;
            default:
                entityMetricType=EntityMetricType.TOTAL;
        }
        UpdateSubscriptionForecastFromActualsCommand command = new UpdateSubscriptionForecastFromActualsCommand(subscriptionAnalyserId,entityMetricType, SysDate.now());
        commandGateway.executeAsync(command);

        //TODO: Assumption is .. there is a single Subscription RuleView object at any time.. to validate if this is true
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/predict/subscriber/{metrictype}")
    public ResponseEntity<String> forecastSubscriberDemand(@PathVariable("metrictype") String metricType) throws Exception {
        final int subscriptionAnalyserId=1;
        EntityMetricType entityMetricType=null;
        switch(metricType){
            case "new":
                entityMetricType=EntityMetricType.NEW;
                break;
            case "churn":
                entityMetricType=EntityMetricType.CHURN;
                break;
            case "total":
                entityMetricType=EntityMetricType.TOTAL;
                break;
            default:
                entityMetricType=EntityMetricType.TOTAL;
        }
        UpdateSubscriberForecastFromActualsCommand command = new UpdateSubscriberForecastFromActualsCommand(subscriptionAnalyserId,entityMetricType, SysDate.now());
        commandGateway.executeAsync(command);

        //TODO: Assumption is .. there is a single Subscription RuleView object at any time.. to validate if this is true
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/predict/deliveries/{metrictype}")
    public ResponseEntity<String> forecastDeliveryDemand(@PathVariable("metrictype") String metricType) throws Exception {
        //List<DeliveriesWeightRangeConfig.WeightRange> weightRanges=deliveriesWeightRangeConfig.getWeightRange();
        final int subscriptionAnalyserId=1;
        EntityMetricType entityMetricType=null;
        switch(metricType){
            case "new":
                entityMetricType=EntityMetricType.NEW;
                break;
            case "churn":
                entityMetricType=EntityMetricType.CHURN;
                break;
            case "total":
                entityMetricType=EntityMetricType.TOTAL;
                break;
            default:
                entityMetricType=EntityMetricType.TOTAL;
        }
        UpdateDeliveryForecastFromActualsCommand command = new UpdateDeliveryForecastFromActualsCommand(subscriptionAnalyserId,entityMetricType,SysDate.now());
        commandGateway.executeAsync(command);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

}
