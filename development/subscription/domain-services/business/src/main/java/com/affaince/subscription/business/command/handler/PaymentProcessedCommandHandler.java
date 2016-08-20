package com.affaince.subscription.business.command.handler;

import com.affaince.subscription.business.command.PaymentProcessedCommand;
import com.affaince.subscription.business.command.domain.BusinessAccount;
import com.affaince.subscription.date.SysDate;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 8/5/16.
 */
@Component
public class PaymentProcessedCommandHandler {
    private final Repository<BusinessAccount> repository;

    @Autowired
    public PaymentProcessedCommandHandler(Repository<BusinessAccount> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(PaymentProcessedCommand command) {
        BusinessAccount businessAccount = repository.load(Integer.valueOf(SysDate.now().getYear()).toString());
        businessAccount.adjustBookingAmount(command.getPaymentAmount());
    }
}
