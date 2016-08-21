package com.affaince.subscription.payments.command.handler;

import com.affaince.subscription.payments.command.DeliveryCreatedCommand;
import com.affaince.subscription.payments.command.domain.Payment;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 21/8/16.
 */
@Component
public class DeliveryCreatedCommandHandler {
    private final Repository<Payment> repository;

    @Autowired
    public DeliveryCreatedCommandHandler(Repository<Payment> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(DeliveryCreatedCommand command) {
        Payment payment = repository.load(command.getSubscriptionId());
        payment.handleDeliveryCreatedCommand(command);
    }
}
