package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.PrepareDeliveryForDispatchCommand;
import com.affaince.subscription.subscriber.command.domain.Subscriber;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 11-12-2016.
 */
@Component
public class PrepareDeliveryForDispatchCommandHandler {

    private final Repository<Subscriber> repository;

    @Autowired
    public PrepareDeliveryForDispatchCommandHandler(Repository<Subscriber> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(PrepareDeliveryForDispatchCommand command) {
        final Subscriber subscriber = repository.load(command.getSubscriberId());
        subscriber.prepareDeliveryForDispatch(command.getDeliveryId(), command.getLatestPriceBucketMap());
    }
}
