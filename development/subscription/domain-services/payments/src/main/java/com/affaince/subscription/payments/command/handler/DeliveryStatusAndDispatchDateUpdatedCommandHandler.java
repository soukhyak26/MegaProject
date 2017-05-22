package com.affaince.subscription.payments.command.handler;

import com.affaince.subscription.payments.command.DeliveryStatusAndDispatchDateUpdatedCommand;
import com.affaince.subscription.payments.command.domain.PaymentAccount;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 5/20/2017.
 */
@Component
public class DeliveryStatusAndDispatchDateUpdatedCommandHandler {
    private Repository<PaymentAccount> paymentAccounts;
    @CommandHandler
    public void handle(DeliveryStatusAndDispatchDateUpdatedCommand command){
        PaymentAccount paymentAccount=paymentAccounts.load(command.getSubscriptionId());
        paymentAccount.correctDues(command);
    }
}
