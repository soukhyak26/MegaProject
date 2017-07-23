package com.affaince.subscription.payments.command.handler;

import com.affaince.subscription.payments.calculator.PaymentCalculatorChain;
import com.affaince.subscription.payments.command.CalculatePaymentInstallmentCommand;
import com.affaince.subscription.payments.command.domain.PaymentAccount;
import com.affaince.subscription.pojos.PaymentExpression;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CalculatePaymentInstallmentCommandHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CalculatePaymentInstallmentCommandHandler.class);

    private final Repository <PaymentAccount> paymentAccounts;
    private final PaymentCalculatorChain paymentCalculatorChain;
    private final ObjectMapper objectMapper;

    @Autowired
    public CalculatePaymentInstallmentCommandHandler(Repository<PaymentAccount> paymentAccounts, PaymentCalculatorChain paymentCalculatorChain, ObjectMapper objectMapper) {
        this.paymentAccounts = paymentAccounts;
        this.paymentCalculatorChain = paymentCalculatorChain;
        this.objectMapper = objectMapper;
    }

    @CommandHandler
    public void handle (CalculatePaymentInstallmentCommand command) {
        PaymentExpression paymentExpression = null;
        try {
            paymentExpression = objectMapper.readValue(command.getPaymentScheme(), PaymentExpression.class);
        } catch (IOException e) {
            LOG.error("Problem during creation of Payment Expression: " + e.getMessage());
        }
        final PaymentAccount paymentAccount = paymentAccounts.load(command.getSubscriptionId());
        paymentAccount.calculatePaymentInstallment(command.getDeliveryWisePriceMap(), paymentExpression,
                paymentCalculatorChain);
    }
}
