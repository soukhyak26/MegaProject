package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.UpdateStatusAndDispatchDateCommand;
import com.affaince.subscription.subscriber.command.domain.Basket;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 04-10-2015.
 */
@Component
public class UpdateStatusAndDispatchDateCommandHandler {
    private final Repository<Basket> repository;

    @Autowired
    public UpdateStatusAndDispatchDateCommandHandler(Repository<Basket> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(UpdateStatusAndDispatchDateCommand command) {
        final Basket basket = repository.load(command.getBasketId());
        basket.updateStatusAndDispatchDate(command);
    }
}
