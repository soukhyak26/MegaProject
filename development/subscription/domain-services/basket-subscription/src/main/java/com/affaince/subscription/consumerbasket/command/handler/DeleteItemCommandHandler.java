package com.affaince.subscription.consumerbasket.command.handler;

import com.affaince.subscription.consumerbasket.command.DeleteItemCommand;
import com.affaince.subscription.consumerbasket.command.domain.ConsumerBasket;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 10-09-2015.
 */
@Component
public class DeleteItemCommandHandler {

    private final Repository<ConsumerBasket> repository;

    @Autowired
    public DeleteItemCommandHandler(Repository<ConsumerBasket> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(DeleteItemCommand command) {
        final ConsumerBasket consumerBasket = repository.load(command.getBasketId());
        consumerBasket.deleteItem(command.getItemId());
    }
}
