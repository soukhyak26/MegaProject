package com.affaince.subscription.consumerbasket.command.handler;

import com.affaince.subscription.consumerbasket.command.AddItemToConsumerBasketCommand;
import com.affaince.subscription.consumerbasket.command.domain.ConsumerBasket;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 23-08-2015.
 */
@Component
public class AddItemToConsumerBasketCommandHandler {

    private final Repository<ConsumerBasket> repository;

    @Autowired
    public AddItemToConsumerBasketCommandHandler(Repository<ConsumerBasket> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(AddItemToConsumerBasketCommand command) {
        ConsumerBasket consumerBasket = repository.load(command.getBasketId());
        consumerBasket.addItemToBasket(command);
    }
}
