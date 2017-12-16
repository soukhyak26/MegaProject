package com.affaince.subscription.payments.command.handler;

import com.affaince.subscription.payments.command.DeleteDeliveryCommand;
import com.affaince.subscription.payments.domain.PaymentAccount;
import com.affaince.subscription.payments.service.DuePaymentCorrectionEngine;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteDeliveryCommandHandler {
    private final Repository<PaymentAccount> repository;
    private DuePaymentCorrectionEngine duePaymentCorrectionEngine;

    @Autowired
    public DeleteDeliveryCommandHandler(Repository<PaymentAccount> repository,DuePaymentCorrectionEngine duePaymentCorrectionEngine) {
        this.duePaymentCorrectionEngine=duePaymentCorrectionEngine;
        this.repository = repository;
    }

    @CommandHandler
    public void handle(DeleteDeliveryCommand command) {
        //TODO: change this to subscription id once event/command contains it (in other domains)
        PaymentAccount paymentAccount = repository.load(command.getSubscriptionId());
        paymentAccount.deleteDelivery(command.getSubscriberId(),command.getSubscriptionId(),command.getDeliveryId(),command.getSequence(),command.getDeletionDate(),duePaymentCorrectionEngine);
    }
}
