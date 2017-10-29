package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.UpdateSubscriptionForecastFromActualsCommand;
import com.affaince.subscription.subscriber.command.domain.SubscriptionAnalyser;
import com.affaince.subscription.subscriber.query.predictions.SubscriptionMetricssHistoryRetriever;
import com.affaince.subscription.subscriber.services.config.SubscriptionRuleService;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 10/2/2017.
 */
@Component
public class UpdateSubscriptionForecastFromActualsCommandHandler {
    private Repository<SubscriptionAnalyser> repository;
    private SubscriptionMetricssHistoryRetriever subscriptionMetricssHistoryRetriever;
    private SubscriptionRuleService subscriptionRuleService;

    @Autowired
    public UpdateSubscriptionForecastFromActualsCommandHandler(Repository<SubscriptionAnalyser> repository, SubscriptionMetricssHistoryRetriever subscriptionMetricssHistoryRetriever, SubscriptionRuleService subscriptionRuleService) {
        this.repository = repository;
        this.subscriptionMetricssHistoryRetriever = subscriptionMetricssHistoryRetriever;
        this.subscriptionRuleService = subscriptionRuleService;
    }

    @CommandHandler
    public void handle(UpdateSubscriptionForecastFromActualsCommand command){
        SubscriptionAnalyser subscriptionAnalyser= repository.load(command.getSubscriptionAnalyserId());
        subscriptionAnalyser.initiateSubscriptionMetricsForecast(command.getEntityMetricType(), subscriptionMetricssHistoryRetriever,subscriptionRuleService);
    }
}
