package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.ApplyPaymentSchemeCommand;
import com.affaince.subscription.subscriber.command.domain.Subscriber;
import com.affaince.subscription.subscriber.command.domain.Subscription;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 7/6/2017.
 */
@Component
public class ApplyPaymentSchemeCommandHandler {
    private final Repository<Subscriber> subscriberRepository;

    @Autowired
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
