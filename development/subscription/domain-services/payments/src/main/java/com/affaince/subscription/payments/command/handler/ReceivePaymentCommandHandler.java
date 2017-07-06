package com.affaince.subscription.payments.command.handler;

import com.affaince.subscription.payments.command.ReceivePaymentCommand;
import com.affaince.subscription.payments.command.domain.PaymentAccount;
import com.affaince.subscription.payments.exception.PaymentReceivedForNonExistentAccountException;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.AggregateNotFoundException;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReceivePaymentCommandHandler {
    private final Repository<PaymentAccount> repository;

    @Autowired
    public ReceivePaymentCommandHandler(Repository<PaymentAccount> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(ReceivePaymentCommand command) {
        /*Payment payment = repository.load(command.getSubscriptionId());
        if(payment == null) {
            payment = new Payment(command.getSubscriptionId(), command.getPaidAmount());
            repository.add(payment);
        } else {
            payment.handlePartialPayment(command.getPaidAmount());
        }*/
        //TODO : check if creation and updation of aggregate can be split
        PaymentAccount paymentAccount;
        try {
            paymentAccount = repository.load(command.getSubscriptionId());
        } catch (AggregateNotFoundException anfe) {
            throw PaymentReceivedForNonExistentAccountException.build(command.getSubscriptionId());
        }
        paymentAccount.addPayment(command.getSubscriptionId(),command.getPaidAmount(),command.getPaymentDate());
    }
}
