package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.SetSubscriberPasswordCommand;
import com.affaince.subscription.subscriber.domain.Subscriber;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;

/**
 * Created by rbsavaliya on 26-12-2015.
 */
@Component
public class SetSubscriberPasswordCommandHandler {

    private final Repository<Subscriber> repository;

    @Autowired
    public SetSubscriberPasswordCommandHandler(Repository<Subscriber> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(SetSubscriberPasswordCommand command) throws NoSuchAlgorithmException {
        final Subscriber subscriber = repository.load(command.getSubscriberId());
        subscriber.setPassword(command.getPassword());
    }
}
