package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.ConfirmSubscriptionCommand;
import com.affaince.subscription.subscriber.command.domain.DeliveryChargesRule;
import com.affaince.subscription.subscriber.command.domain.Subscriber;
import com.affaince.subscription.subscriber.command.domain.Subscription;
import com.affaince.subscription.subscriber.services.deliverychargesrule.DeliveryChargesRulesService;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rsavaliya on 17/1/16.
 */
@Component
public class ConfirmSubscriptionCommandHandler {
    private final Repository<Subscriber> subscriberRepository;
    private final DeliveryChargesRulesService deliveryChargesRulesService;

    @Autowired
    public ConfirmSubscriptionCommandHandler(Repository<Subscriber> subscriberRepository, DeliveryChargesRulesService deliveryChargesRulesService) {
        this.subscriberRepository = subscriberRepository;
        this.deliveryChargesRulesService = deliveryChargesRulesService;
    }

    @CommandHandler
    public void handler(ConfirmSubscriptionCommand command) {
        final Subscriber subscriber = subscriberRepository.load(command.getSubscriberId());
        final Subscription subscription = subscriber.getSubscription();
        final DeliveryChargesRule deliveryChargesRule = deliveryChargesRulesService.findActiveDeliveryChargesRule();
        subscriber.confirmSubscription(subscription, deliveryChargesRule);
    }
}
