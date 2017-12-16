package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.DeriveSubscriptionsInAllCostRangesFromActualsCommand;
import com.affaince.subscription.subscriber.domain.SubscriptionAnalyser;
import com.affaince.subscription.subscriber.query.predictions.SubscriptionHistoryRetriever;
import com.affaince.subscription.subscriber.services.config.SubscriptionRuleService;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mandar on 10/29/2017.
 */
public class DeriveSubscriptionsInAllCostRangesFromActualsCommandHandler {
    private Repository<SubscriptionAnalyser> repository;
    private SubscriptionHistoryRetriever subscriptionHistoryRetriever;
    private SubscriptionRuleService subscriptionRuleService;
    @Autowired
    public DeriveSubscriptionsInAllCostRangesFromActualsCommandHandler(Repository<SubscriptionAnalyser> repository, SubscriptionRuleService subscriptionRuleService, SubscriptionHistoryRetriever subscriptionHistoryRetriever) {
        this.repository = repository;
        this.subscriptionHistoryRetriever = subscriptionHistoryRetriever;
        this.subscriptionRuleService = subscriptionRuleService;
    }

    @CommandHandler
    public void handle(DeriveSubscriptionsInAllCostRangesFromActualsCommand command){
        SubscriptionAnalyser subscriptionAnalyser= repository.load(command.getSubscriptionAnalyserId());
        subscriptionAnalyser.initiateSubscriptionForecast(command.getEntityMetricType(), subscriptionRuleService, subscriptionHistoryRetriever);
    }

}
