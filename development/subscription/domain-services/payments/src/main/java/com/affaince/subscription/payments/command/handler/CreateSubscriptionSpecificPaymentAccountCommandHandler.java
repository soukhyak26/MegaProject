package com.affaince.subscription.payments.command.handler;

import com.affaince.subscription.payments.command.CreateSubscriptionSpecificPaymentAccountCommand;
import com.affaince.subscription.payments.command.domain.PaymentAccount;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mandar on 5/17/2017.
 */
public class CreateSubscriptionSpecificPaymentAccountCommandHandler {
    private final Repository<PaymentAccount> repository;

    @Autowired
    public CreateSubscriptionSpecificPaymentAccountCommandHandler(Repository<PaymentAccount> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(CreateSubscriptionSpecificPaymentAccountCommand command) {
        PaymentAccount paymentAccount= new PaymentAccount(command.getSubscriberId(),command.getSubscriptionId());
        repository.add(paymentAccount);
        paymentAccount.registerSubscriptionDetails(command.getTotalSubscriptionDeliveries());
    }
}
