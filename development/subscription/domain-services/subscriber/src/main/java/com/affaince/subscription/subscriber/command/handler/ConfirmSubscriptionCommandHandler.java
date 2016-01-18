package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.ConfirmSubscriptionCommand;
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

    @Autowired
    public ConfirmSubscriptionCommandHandler(Repository<Subscription> subscriptionRepository, Repository<Subscriber> subscriberRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriberRepository = subscriberRepository;
    }

    @CommandHandler
    public void handler(ConfirmSubscriptionCommand command) {
        Subscription subscription = subscriptionRepository.load(command.getSubscriptionId());
        Subscriber subscriber = subscriberRepository.load(subscription.getSubscriberId());

        subscriber.confirmSubscription(subscription);
    }
}
