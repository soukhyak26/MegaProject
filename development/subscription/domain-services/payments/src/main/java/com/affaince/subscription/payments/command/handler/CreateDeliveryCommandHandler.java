package com.affaince.subscription.payments.command.handler;

import com.affaince.subscription.payments.command.CreateDeliveryCommand;
import com.affaince.subscription.payments.command.domain.PaymentAccount;
import com.affaince.subscription.payments.service.DuePaymentCorrectionEngine;
import com.affaince.subscription.payments.service.ProductDetailsService;
import com.affaince.subscription.payments.service.TaggedPricingService;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateDeliveryCommandHandler {
    private final Repository<PaymentAccount> repository;
    private final TaggedPricingService taggedPricingService;
    private final ProductDetailsService productDetailsService;

    @Autowired
    public CreateDeliveryCommandHandler(Repository<PaymentAccount> repository,TaggedPricingService taggedPricingService,ProductDetailsService productDetailsService) {
        this.repository = repository;
        this.taggedPricingService=taggedPricingService;
        this.productDetailsService=productDetailsService;
    }

    @CommandHandler
    public void handle(CreateDeliveryCommand command) {
        PaymentAccount paymentAccount = repository.load(command.getSubscriptionId());
        paymentAccount.createdNewDelivery(command,taggedPricingService,productDetailsService);
    }
}
