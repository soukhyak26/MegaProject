package com.affaince.subscription.payments.command.handler;

import com.affaince.subscription.payments.command.ApproveDeliveryDispatchCommand;
import com.affaince.subscription.payments.command.domain.PaymentAccount;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mandar on 7/11/2017.
 */
public class ApproveDeliveryDispatchCommandHandler {
    private final Repository<PaymentAccount> paymentAccountRepository;

    @Autowired
    public ApproveDeliveryDispatchCommandHandler(Repository<PaymentAccount> paymentAccountRepository) {
        this.paymentAccountRepository = paymentAccountRepository;
    }

    @CommandHandler
    public void handle(ApproveDeliveryDispatchCommand command){
        PaymentAccount paymentAccount=paymentAccountRepository.load(command.getSubscriptionId());
        paymentAccount.validateAndApproveDelivery(command.getDeliveryId(),command.getSequence());
    }
}
