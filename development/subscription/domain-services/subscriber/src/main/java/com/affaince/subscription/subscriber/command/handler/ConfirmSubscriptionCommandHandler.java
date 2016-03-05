package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.common.type.DeliveryChargesRuleType;
import com.affaince.subscription.subscriber.command.ConfirmSubscriptionCommand;
import com.affaince.subscription.subscriber.command.domain.DeliveryChargesRule;
import com.affaince.subscription.subscriber.command.domain.Subscriber;
import com.affaince.subscription.subscriber.command.domain.Subscription;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rsavaliya on 17/1/16.
 */
@Component
public class ConfirmSubscriptionCommandHandler {
    private final Repository<Subscription> subscriptionRepository;
    private final Repository<Subscriber> subscriberRepository;
    private final Repository<DeliveryChargesRule> deliveryChargesRuleRepository;

    @Autowired
    public ConfirmSubscriptionCommandHandler(Repository<Subscription> subscriptionRepository, Repository<Subscriber> subscriberRepository, Repository<DeliveryChargesRule> deliveryChargesRuleRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriberRepository = subscriberRepository;
        this.deliveryChargesRuleRepository = deliveryChargesRuleRepository;
    }

    @CommandHandler
    public void handler(ConfirmSubscriptionCommand command) {
        final Subscription subscription = subscriptionRepository.load(command.getSubscriptionId());
        final Subscriber subscriber = subscriberRepository.load(subscription.getSubscriberId());
        final DeliveryChargesRule deliveryChargesRule = deliveryChargesRuleRepository.load(
                DeliveryChargesRuleType.CHARGES_ON_DELIVERY_WEIGHT.getDeliveryChargesRuleTypeCode()
        );
        subscriber.confirmSubscription(subscription, deliveryChargesRule);
    }
}
