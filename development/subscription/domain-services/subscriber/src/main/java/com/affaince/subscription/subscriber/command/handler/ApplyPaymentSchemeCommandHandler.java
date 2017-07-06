package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.ApplyPaymentSchemeCommand;
import com.affaince.subscription.subscriber.command.domain.Subscriber;
import com.affaince.subscription.subscriber.command.domain.Subscription;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;

/**
 * Created by mandar on 7/6/2017.
 */
public class ApplyPaymentSchemeCommandHandler {
    private final Repository<Subscriber> subscriberRepository;

    public ApplyPaymentSchemeCommandHandler(Repository<Subscriber> subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }
    @CommandHandler
    public void handle(ApplyPaymentSchemeCommand command){
        final Subscriber subscriber = subscriberRepository.load(command.getSubscriberId());
        Subscription subscription = subscriber.getSubscription();
        subscription.setPaymentScheme(command.getPaymentSchemeId());
    }
}
