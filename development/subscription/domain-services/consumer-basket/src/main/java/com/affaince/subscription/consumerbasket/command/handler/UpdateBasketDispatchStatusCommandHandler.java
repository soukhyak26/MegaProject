package com.affaince.subscription.consumerbasket.command.handler;

import com.affaince.subscription.consumerbasket.command.UpdateBasketDispatchStatusCommand;
import com.affaince.subscription.consumerbasket.command.domain.ConsumerBasket;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandark on 30-08-2015.
 */
@Component
public class UpdateBasketDispatchStatusCommandHandler {
    private final Repository<ConsumerBasket> repository;

    @Autowired
    public UpdateBasketDispatchStatusCommandHandler(Repository<ConsumerBasket> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(UpdateBasketDispatchStatusCommand updateBasketDispatchStatusCommand){
        final ConsumerBasket consumerBasket = repository.load(updateBasketDispatchStatusCommand.getBasketId());
        consumerBasket.updateBasketStatus(updateBasketDispatchStatusCommand.getDispactchStatusCode(),updateBasketDispatchStatusCommand.getReasonCode(),updateBasketDispatchStatusCommand.getDispatchDate());

    }
}
