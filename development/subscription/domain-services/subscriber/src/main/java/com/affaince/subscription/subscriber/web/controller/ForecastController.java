package com.affaince.subscription.subscriber.web.controller;

import com.affaince.subscription.common.vo.EntityMetricType;
import com.affaince.subscription.common.vo.EntityType;
import com.affaince.subscription.subscriber.configuration.DeliveriesWeightRangeConfig;
import com.affaince.subscription.subscriber.query.predictions.DeliveryHistoryRetriever;
import com.affaince.subscription.subscriber.query.predictions.SubscribersHistoryRetriever;
import com.affaince.subscription.subscriber.query.predictions.SubscriptionsHistoryRetriever;
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
    DeliveriesWeightRangeConfig deliveriesWeightRangeConfig;
    @Autowired
    public ForecastController(SubscriptionsHistoryRetriever subscriptionsHistoryRetriever, SubscribersHistoryRetriever subscribersHistoryRetriever,DeliveryHistoryRetriever deliveryHistoryRetriever,DeliveriesWeightRangeConfig deliveriesWeightRangeConfig) {
        this.subscriptionsHistoryRetriever = subscriptionsHistoryRetriever;
        this.subscribersHistoryRetriever = subscribersHistoryRetriever;
        this.deliveryHistoryRetriever=deliveryHistoryRetriever;
        this.deliveriesWeightRangeConfig=deliveriesWeightRangeConfig;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/predict/subscription")
    public ResponseEntity<String> forecastSubscriptionDemand() throws Exception {
        Map<String,Object> metadata= new HashMap<>();
        metadata.put("ENTITY_TYPE", EntityType.SUBSCRIPTION);
        metadata.put("ENTITY_METRIC_TYPE", EntityMetricType.TOTAL);
        subscriptionsHistoryRetriever.marshallSendAndReceive(null,metadata);
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
        List<DeliveriesWeightRangeConfig.WeightRange> weightRanges=deliveriesWeightRangeConfig.getWeightRange();
        for (DeliveriesWeightRangeConfig.WeightRange weightRange: weightRanges) {
            Map<String,Object> metadata= new HashMap<>();
            metadata.put("ENTITY_TYPE", EntityType.DELIVERY);
            metadata.put("ENTITY_METRIC_TYPE", EntityMetricType.TOTAL);
            metadata.put("MIN_WEIGHT", weightRange.getMin());
            metadata.put("MAX_WEIGHT",weightRange.getMax());
            deliveryHistoryRetriever.marshallSendAndReceive(null,metadata);
        }
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

}
