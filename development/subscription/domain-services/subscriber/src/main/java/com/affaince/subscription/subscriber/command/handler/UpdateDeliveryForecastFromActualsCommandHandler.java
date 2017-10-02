package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.UpdateDeliveryForecastFromActualsCommand;
import com.affaince.subscription.subscriber.command.domain.SubscriptionAnalyser;
import com.affaince.subscription.subscriber.query.predictions.DeliveryHistoryRetriever;
import com.affaince.subscription.subscriber.services.config.DeliveryChargesRuleService;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 10/2/2017.
 */
@Component
public class UpdateDeliveryForecastFromActualsCommandHandler {
    private Repository<SubscriptionAnalyser> repository;
    private DeliveryHistoryRetriever deliveryHistoryRetriever;
    private DeliveryChargesRuleService deliveryChargesRuleService;
    @Autowired
    public UpdateDeliveryForecastFromActualsCommandHandler(Repository<SubscriptionAnalyser> repository,DeliveryChargesRuleService deliveryChargesRuleService, DeliveryHistoryRetriever deliveryHistoryRetriever) {
        this.repository = repository;
        this.deliveryHistoryRetriever = deliveryHistoryRetriever;
        this.deliveryChargesRuleService=deliveryChargesRuleService;
    }

    @CommandHandler
    public void handle(UpdateDeliveryForecastFromActualsCommand command){
        SubscriptionAnalyser subscriptionAnalyser= repository.load(command.getSubscriptionAnalyserId());
        subscriptionAnalyser.initiateDeliveryForecast(command.getEntityMetricType(),deliveryChargesRuleService,deliveryHistoryRetriever);
    }
}
