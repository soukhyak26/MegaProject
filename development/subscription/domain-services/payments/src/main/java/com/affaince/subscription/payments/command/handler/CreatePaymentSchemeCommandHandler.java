package com.affaince.subscription.payments.command.handler;

import com.affaince.subscription.compiler.paymentscheme.PaymentSchemeCompiler;
import com.affaince.subscription.payments.command.CreatePaymentSchemeCommand;
import com.affaince.subscription.payments.domain.PaymentScheme;
import com.affaince.subscription.pojos.PaymentExpression;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 5/20/2017.
 */
@Component
public class CreatePaymentSchemeCommandHandler {
    private final Repository<PaymentScheme> paymentSchemes;

    @Autowired
    public CreatePaymentSchemeCommandHandler(Repository<PaymentScheme> paymentSchemes) {
        this.paymentSchemes = paymentSchemes;
    }

    @CommandHandler
    public void handle(CreatePaymentSchemeCommand command) {
        PaymentSchemeCompiler paymentSchemeCompiler = new PaymentSchemeCompiler();
        PaymentExpression paymentExpression = paymentSchemeCompiler.compile(command.getPaymentSchemeRule());
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(paymentExpression);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        PaymentScheme paymentScheme = new PaymentScheme(command.getPaymentSchemeId(), command.getPaymentSchemeName(), command.getPaymentSchemeDescription(), jsonString,command.getPaymentSchemeRule(), command.getStartDate(), command.getEndDate());
        paymentSchemes.add(paymentScheme);
    }
}
