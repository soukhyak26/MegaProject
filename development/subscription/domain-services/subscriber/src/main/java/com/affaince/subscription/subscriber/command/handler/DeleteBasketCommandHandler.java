package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.DeleteBasketCommand;
import com.affaince.subscription.subscriber.command.domain.Subscriber;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 11-10-2015.
 */
@Component
public class DeleteBasketCommandHandler {

    private final Repository<Subscriber> subscriberRepository;

    @Autowired
    public DeleteBasketCommandHandler(Repository<Subscriber> subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    @CommandHandler
    public void handle(DeleteBasketCommand command) {
        final Subscriber subscriber = subscriberRepository.load(command.getSubscriberId());
        subscriber.deleteBasket(command);
    }
}
