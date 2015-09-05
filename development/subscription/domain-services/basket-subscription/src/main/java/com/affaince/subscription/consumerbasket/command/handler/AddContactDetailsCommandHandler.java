package com.affaince.subscription.consumerbasket.command.handler;

import com.affaince.subscription.consumerbasket.command.AddContactDetailsCommand;
import com.affaince.subscription.consumerbasket.command.domain.ConsumerBasket;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 02-08-2015.
 */
@Component
public class AddContactDetailsCommandHandler {

    Repository<ConsumerBasket> repository;

    @Autowired
    public AddContactDetailsCommandHandler(Repository<ConsumerBasket> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(AddContactDetailsCommand command) {
        ConsumerBasket consumerBasket = repository.load(command.getBasketId());
        consumerBasket.updateContactDetails(command.getEmail(), command.getMobileNumber(), command.getAlternativeNumber());
    }
}
