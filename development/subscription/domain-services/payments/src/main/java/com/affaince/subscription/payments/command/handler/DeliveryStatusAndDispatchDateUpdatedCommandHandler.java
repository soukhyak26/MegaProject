package com.affaince.subscription.payments.command.handler;

import com.affaince.subscription.payments.command.DeliveryStatusAndDispatchDateUpdatedCommand;
import com.affaince.subscription.payments.command.domain.PaymentAccount;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 21/8/16.
 */
@Component
public class DeliveryStatusAndDispatchDateUpdatedCommandHandler {
    private final Repository<PaymentAccount> repository;

    @Autowired
    public DeliveryStatusAndDispatchDateUpdatedCommandHandler(Repository<PaymentAccount> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(DeliveryStatusAndDispatchDateUpdatedCommand command) {
        PaymentAccount paymentAccount = repository.load(command.getSubscriptionId());
        paymentAccount.handleDeliveryStatusAndDispatchDateUpdatedCommand(command);
    }

}
