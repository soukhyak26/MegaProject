package com.affaince.subscription.subscriber.web.controller;

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
    @Autowired
    public ForecastController(SubscriptionsHistoryRetriever subscriptionsHistoryRetriever, SubscribersHistoryRetriever subscribersHistoryRetriever) {
        this.subscriptionsHistoryRetriever = subscriptionsHistoryRetriever;
        this.subscribersHistoryRetriever = subscribersHistoryRetriever;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/predict/subscription")
    public ResponseEntity<String> forecastSubscriptionDemand() throws Exception {
        subscriptionsHistoryRetriever.marshallSendAndReceive(null);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/predict/subscriber")
    public ResponseEntity<String> forecastSubscriberDemand() throws Exception {
        subscribersHistoryRetriever.marshallSendAndReceive(null);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

}
