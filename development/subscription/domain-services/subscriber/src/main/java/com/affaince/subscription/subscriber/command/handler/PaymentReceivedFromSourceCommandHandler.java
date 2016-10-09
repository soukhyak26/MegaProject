package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.common.type.ConsumerBasketActivationStatus;
import com.affaince.subscription.subscriber.command.domain.Subscriber;
import com.affaince.subscription.subscriber.command.domain.Subscription;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 16-01-2016.
 */
@Component
public class PaymentReceivedFromSourceCommandHandler {

    private final Repository<Subscriber> repository;

    @Autowired
    public PaymentReceivedFromSourceCommandHandler(Repository<Subscriber> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(com.affaince.subscription.subscriber.command.PaymentReceivedFromSourceCommand command) {
        final Subscriber subscriber = repository.load(command.getSubscriberId());
        final Subscription subscription = subscriber.getSubscription();
        if (!subscription.getConsumerBasketStatus().equals(ConsumerBasketActivationStatus.ACTIVATED)) {
            subscription.activateSubscription();
        }
        subscription.addReceivedPayment(command);
    }
}
