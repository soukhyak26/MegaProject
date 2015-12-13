package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.UpdateStatusAndDispatchDateCommand;
import com.affaince.subscription.subscriber.command.domain.Subscriber;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 04-10-2015.
 */
@Component
public class UpdateStatusAndDispatchDateCommandHandler {
    private final Repository<Subscriber> repository;

    @Autowired
    public UpdateStatusAndDispatchDateCommandHandler(Repository<Subscriber> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(UpdateStatusAndDispatchDateCommand command) {
        final Subscriber subscriber = repository.load(command.getSubscriberId());
        subscriber.updateStatusAndDispatchDate(command);
    }
}
