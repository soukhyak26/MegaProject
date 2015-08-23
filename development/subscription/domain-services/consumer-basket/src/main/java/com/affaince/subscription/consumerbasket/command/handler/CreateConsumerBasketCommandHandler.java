package com.affaince.subscription.consumerbasket.command.handler;

import com.affaince.subscription.consumerbasket.command.CreateConsumerBasketCommand;
import com.affaince.subscription.consumerbasket.command.domain.ConsumerBasket;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 09-08-2015.
 */
@Component
public class CreateConsumerBasketCommandHandler {

    private final Repository<ConsumerBasket> repository;

    @Autowired
    public CreateConsumerBasketCommandHandler(Repository<ConsumerBasket> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(CreateConsumerBasketCommand command) {
        ConsumerBasket consumerBasket = new ConsumerBasket(command.getBasketId(), command.getUserId());
        repository.add(consumerBasket);
    }
}
