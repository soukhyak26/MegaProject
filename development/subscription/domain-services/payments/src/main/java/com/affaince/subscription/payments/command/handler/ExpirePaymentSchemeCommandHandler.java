package com.affaince.subscription.payments.command.handler;

import com.affaince.subscription.payments.command.ExpirePaymentSchemeCommand;
import com.affaince.subscription.payments.domain.PaymentScheme;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 5/20/2017.
 */
@Component
public class ExpirePaymentSchemeCommandHandler {
    private Repository<PaymentScheme> paymentSchemes;
    @Autowired
    public ExpirePaymentSchemeCommandHandler(Repository<PaymentScheme> paymentSchemes) {
        this.paymentSchemes = paymentSchemes;
    }
    @CommandHandler
    public void handle(ExpirePaymentSchemeCommand command){
        PaymentScheme paymentScheme =paymentSchemes.load(command.getSchemeId());
        paymentScheme.expireScheme(command.getExpiryDate());
    }
}
