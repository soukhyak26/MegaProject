package com.affaince.subscription.payments.command.handler;

import com.affaince.subscription.payments.command.UpdateDeliveryStatusAndDispatchDateCommand;
import com.affaince.subscription.payments.command.domain.PaymentAccount;
import com.affaince.subscription.payments.service.DuePaymentCorrectionEngine;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 5/20/2017.
 */
@Component
public class UpdateDeliveryStatusAndDispatchDateCommandHandler {
    private final Repository<PaymentAccount> paymentAccounts;
    private final DuePaymentCorrectionEngine duePaymentCorrectionEngine;

    public UpdateDeliveryStatusAndDispatchDateCommandHandler(Repository<PaymentAccount> paymentAccounts, DuePaymentCorrectionEngine duePaymentCorrectionEngine) {
        this.paymentAccounts = paymentAccounts;
        this.duePaymentCorrectionEngine = duePaymentCorrectionEngine;
    }

    @CommandHandler
    public void handle(UpdateDeliveryStatusAndDispatchDateCommand command){
        PaymentAccount paymentAccount=paymentAccounts.load(command.getSubscriptionId());
        paymentAccount.correctDues(command,duePaymentCorrectionEngine);
    }
}
