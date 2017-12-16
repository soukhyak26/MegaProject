package com.affaince.subscription.subscriber.web.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mandar on 9/1/2017.
 */
@RestController
@RequestMapping(value = "/subscriber/forecast")
@Component

public class ForecastController {
    /*private static final Logger LOGGER = LoggerFactory.getLogger(ForecastController.class);
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
*/
}
