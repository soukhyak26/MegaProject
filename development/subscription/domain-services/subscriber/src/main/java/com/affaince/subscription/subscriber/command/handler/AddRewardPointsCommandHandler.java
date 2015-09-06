package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.AddRewardPointsCommand;
import com.affaince.subscription.subscriber.command.domain.Subscriber;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 06-09-2015.
 */
@Component
public class AddRewardPointsCommandHandler {

    private final Repository<Subscriber> repository;

    @Autowired
    public AddRewardPointsCommandHandler(Repository<Subscriber> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(AddRewardPointsCommand command) {
        Subscriber subscriber = repository.load(command.getSubscriberId());
        subscriber.addRewardPoints (command.getRewardPoints());
    }
}
