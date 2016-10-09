package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.UpdateBasketDispatchStatusCommand;
import com.affaince.subscription.subscriber.command.domain.Subscriber;
import com.affaince.subscription.subscriber.command.domain.Subscription;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandark on 30-08-2015.
 */
@Component
public class UpdateBasketDispatchStatusCommandHandler {
    private final Repository<Subscriber> repository;

    @Autowired
    public UpdateBasketDispatchStatusCommandHandler(Repository<Subscriber> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(UpdateBasketDispatchStatusCommand command) {
        final Subscriber subscriber = repository.load(command.getSubscriberId());
        final Subscription subscription = subscriber.getSubscription();
        subscription.updateBasketStatus(command.getDispatchStatusCode(), command.getReasonCode(), command.getDispatchDate());

    }
}
