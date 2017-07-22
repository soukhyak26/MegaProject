package com.affaince.subscription.payments.command.handler;

import com.affaince.subscription.payments.command.CalculatePaymentInstallmentCommand;
import com.affaince.subscription.payments.command.domain.PaymentAccount;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalculatePaymentInstallmentCommandHandler {

    private final Repository <PaymentAccount> paymentAccounts;

    @Autowired
    public CalculatePaymentInstallmentCommandHandler(Repository<PaymentAccount> paymentAccounts) {
        this.paymentAccounts = paymentAccounts;
    }

    @CommandHandler
    public void handle (CalculatePaymentInstallmentCommand command) {

    }
}
