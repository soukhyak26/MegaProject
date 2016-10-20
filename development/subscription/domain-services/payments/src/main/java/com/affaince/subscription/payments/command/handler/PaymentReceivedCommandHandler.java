package com.affaince.subscription.payments.command.handler;

import com.affaince.subscription.payments.command.PaymentReceivedCommand;
import com.affaince.subscription.payments.command.domain.Payment;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.AggregateNotFoundException;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 21/8/16.
 */
@Component
public class PaymentReceivedCommandHandler {
    private final Repository<Payment> repository;

    @Autowired
    public PaymentReceivedCommandHandler(Repository<Payment> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(PaymentReceivedCommand command) {
        /*Payment payment = repository.load(command.getSubscriptionId());
        if(payment == null) {
            payment = new Payment(command.getSubscriptionId(), command.getPaidAmount());
            repository.add(payment);
        } else {
            payment.handlePartialPayment(command.getPaidAmount());
        }*/
        //TODO : check if creation and updation of aggregate can be split
        Payment payment;
        try {
            payment = repository.load(command.getSubscriptionId());
            payment.handlePartialPayment(command.getPaidAmount());
        } catch (AggregateNotFoundException anfe) {
            payment = new Payment(command.getSubscriptionId(), command.getPaidAmount());
            repository.add(payment);
        }
    }
}
