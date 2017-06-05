package com.affaince.subscription.payments.command.handler;

import com.affaince.subscription.payments.command.CorrectDuePaymentCommand;
import com.affaince.subscription.payments.command.domain.PaymentAccount;
import com.affaince.subscription.payments.service.DuePaymentCorrectionEngine;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 5/20/2017.
 */
@Component
public class CorrectDuePaymentCommandHandler {
    private final Repository<PaymentAccount> paymentAccounts;
    private final DuePaymentCorrectionEngine duePaymentCorrectionEngine;

    public CorrectDuePaymentCommandHandler(Repository<PaymentAccount> paymentAccounts, DuePaymentCorrectionEngine duePaymentCorrectionEngine) {
        this.paymentAccounts = paymentAccounts;
        this.duePaymentCorrectionEngine = duePaymentCorrectionEngine;
    }

    @CommandHandler
    public void handle(CorrectDuePaymentCommand command){
        PaymentAccount paymentAccount=paymentAccounts.load(command.getSubscriptionId());
        paymentAccount.correctDues(command,duePaymentCorrectionEngine);
    }
}
