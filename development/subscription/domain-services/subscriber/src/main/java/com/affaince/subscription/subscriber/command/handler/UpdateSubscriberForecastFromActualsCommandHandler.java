package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.UpdateSubscriberForecastFromActualsCommand;
import com.affaince.subscription.subscriber.command.domain.SubscriptionAnalyser;
import com.affaince.subscription.subscriber.query.predictions.SubscriberMetricsHistoryRetriever;
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
    private SubscriberMetricsHistoryRetriever subscriberMetricsHistoryRetriever;

    @Autowired
    public UpdateSubscriberForecastFromActualsCommandHandler(Repository<SubscriptionAnalyser> repository,SubscriberMetricsHistoryRetriever subscriberMetricsHistoryRetriever) {
        this.repository = repository;
        this.subscriberMetricsHistoryRetriever = subscriberMetricsHistoryRetriever;
    }

    @CommandHandler
    public void handle(UpdateSubscriberForecastFromActualsCommand command){
        SubscriptionAnalyser subscriptionAnalyser= repository.load(command.getSubscriptionAnalyserId());
        subscriptionAnalyser.initiateSubscriberMetricsForecast(command.getEntityMetricType(), subscriberMetricsHistoryRetriever);
    }
}
