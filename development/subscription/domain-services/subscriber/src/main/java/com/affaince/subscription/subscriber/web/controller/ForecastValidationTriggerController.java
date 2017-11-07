package com.affaince.subscription.subscriber.web.controller;

import com.affaince.subscription.subscriber.services.triggers.DeliveryMetricsForecastingTrigger;
import com.affaince.subscription.subscriber.services.triggers.SubscriberMetricsForecastingTrigger;
import com.affaince.subscription.subscriber.services.triggers.SubscriptionMetricsForecastingTrigger;
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

import java.util.List;

/**
 * Created by mandar on 11/4/2017.
 */
@RestController
@RequestMapping(value = "/triggerforecast")
@Component
public class ForecastValidationTriggerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ForecastValidationTriggerController.class);
    private final SubscriberMetricsForecastingTrigger subscriberMetricsForecastingTrigger;
    private final SubscriptionMetricsForecastingTrigger subscriptionMetricsForecastingTrigger;
    private final DeliveryMetricsForecastingTrigger deliveryMetricsForecastingTrigger;
    @Autowired
    public ForecastValidationTriggerController(SubscriberMetricsForecastingTrigger subscriberMetricsForecastingTrigger, SubscriptionMetricsForecastingTrigger subscriptionMetricsForecastingTrigger,DeliveryMetricsForecastingTrigger deliveryMetricsForecastingTrigger) {
        this.subscriberMetricsForecastingTrigger = subscriberMetricsForecastingTrigger;
        this.subscriptionMetricsForecastingTrigger = subscriptionMetricsForecastingTrigger;
        this.deliveryMetricsForecastingTrigger=deliveryMetricsForecastingTrigger;
    }

    @RequestMapping(method = RequestMethod.GET, value = "shouldtrigger/subscriber")
    public ResponseEntity<Boolean> shouldTriggerSubscriberForecast() {
        return new ResponseEntity<Boolean>(subscriberMetricsForecastingTrigger.shouldTriggerSubscribersMetricsForecast(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "shouldtrigger/subscription")
    public ResponseEntity<Boolean> shouldTriggerSubscriptionForecast() {
        return new ResponseEntity<Boolean>(subscriptionMetricsForecastingTrigger.shouldTriggerSubscriptionMetricsForecast(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "shouldtrigger/delivery/{minWeight}/{maxWeight}")
    public ResponseEntity<Boolean> shouldTriggerDeliveryForecast(@PathVariable double minWeight, @PathVariable double maxWeight) {
        return new ResponseEntity<Boolean>(deliveryMetricsForecastingTrigger.shouldTriggerDeliveryMetricsForecast(minWeight,maxWeight),HttpStatus.OK);
    }

}
