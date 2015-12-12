package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.UpdateBasketDispatchStatusCommand;
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
    private final Repository<Subscription> repository;

    @Autowired
    public UpdateBasketDispatchStatusCommandHandler(Repository<Subscription> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(UpdateBasketDispatchStatusCommand updateBasketDispatchStatusCommand) {
        final Subscription subscription = repository.load(updateBasketDispatchStatusCommand.getBasketId());
        subscription.updateBasketStatus(updateBasketDispatchStatusCommand.getDispatchStatusCode(), updateBasketDispatchStatusCommand.getReasonCode(), updateBasketDispatchStatusCommand.getDispatchDate());

    }
}
