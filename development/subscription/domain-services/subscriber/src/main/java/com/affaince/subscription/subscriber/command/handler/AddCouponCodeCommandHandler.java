package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.AddCouponCodeCommand;
import com.affaince.subscription.subscriber.command.domain.Subscriber;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 06-09-2015.
 */
@Component
public class AddCouponCodeCommandHandler {

    private final Repository<Subscriber> repository;

    @Autowired
    public AddCouponCodeCommandHandler(Repository<Subscriber> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(AddCouponCodeCommand command) {
        Subscriber subscriber = repository.load(command.getSubscriberId());
        subscriber.addCouponCode(command.getCouponCode());
    }
}
