package com.affaince.subscription.payments.command.handler;

import com.affaince.subscription.payments.command.SetPaymentSchemeCommand;
import com.affaince.subscription.payments.domain.PaymentAccount;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 7/9/2017.
 */
@Component
public class SetPaymentSchemeCommandHandler {
    private final Repository<PaymentAccount> paymentAccountRepository;
    @Autowired
    public SetPaymentSchemeCommandHandler(Repository<PaymentAccount> paymentAccountRepository) {
        this.paymentAccountRepository = paymentAccountRepository;
    }

    @CommandHandler
    public void handle(SetPaymentSchemeCommand command){
        PaymentAccount paymentAccount=paymentAccountRepository.load(command.getSubscriptionId());
        paymentAccount.setPaymentScheme(command.getPaymentSchemeId());
    }
}
