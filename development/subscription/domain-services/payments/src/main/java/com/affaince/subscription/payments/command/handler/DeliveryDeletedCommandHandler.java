package com.affaince.subscription.payments.command.handler;

import com.affaince.subscription.payments.command.DeliveryDeletedCommand;
import com.affaince.subscription.payments.command.domain.PaymentAccount;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 20/11/16.
 */
@Component
public class DeliveryDeletedCommandHandler {
    private final Repository<PaymentAccount> repository;

    @Autowired
    public DeliveryDeletedCommandHandler(Repository<PaymentAccount> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(DeliveryDeletedCommand command) {
        //TODO: change this to subscription id once event/command contains it (in other domains)
        PaymentAccount paymentAccount = repository.load(command.getSubscriberId());
        paymentAccount.handleDeliveryDeletedCommand(command);
    }
}
