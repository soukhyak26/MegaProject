package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.UpdateSubscriberForecastFromActualsCommand;
import com.affaince.subscription.subscriber.command.UpdateSubscriptionForecastFromActualsCommand;
import com.affaince.subscription.subscriber.command.domain.SubscriptionAnalyser;
import com.affaince.subscription.subscriber.query.predictions.SubscribersHistoryRetriever;
import com.affaince.subscription.subscriber.query.predictions.SubscriptionsHistoryRetriever;
import com.affaince.subscription.subscriber.services.config.SubscriptionRuleService;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 10/2/2017.
 */
@Component
public class UpdateSubscriberForecastFromActualsCommandHandler {
    private Repository<SubscriptionAnalyser> repository;
    private SubscribersHistoryRetriever subscribersHistoryRetriever;

    @Autowired
    public UpdateSubscriberForecastFromActualsCommandHandler(Repository<SubscriptionAnalyser> repository,SubscribersHistoryRetriever subscribersHistoryRetriever) {
        this.repository = repository;
        this.subscribersHistoryRetriever=subscribersHistoryRetriever;
    }

    @CommandHandler
    public void handle(UpdateSubscriberForecastFromActualsCommand command){
        SubscriptionAnalyser subscriptionAnalyser= repository.load(command.getSubscriptionAnalyserId());
        subscriptionAnalyser.initiateSubscriberForecast(command.getEntityMetricType(),subscribersHistoryRetriever);
    }
}
