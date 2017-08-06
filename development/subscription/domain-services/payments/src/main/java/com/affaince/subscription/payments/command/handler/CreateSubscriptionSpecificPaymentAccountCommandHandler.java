package com.affaince.subscription.payments.command.handler;

import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.payments.command.CreateSubscriptionSpecificPaymentAccountCommand;
import com.affaince.subscription.payments.command.domain.PaymentAccount;
import com.affaince.subscription.payments.service.ProductDetailsService;
import com.affaince.subscription.payments.service.TaggedPricingService;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 5/17/2017.
 */
@Component
public class CreateSubscriptionSpecificPaymentAccountCommandHandler {
    private final Repository<PaymentAccount> repository;
    private final TaggedPricingService taggedPricingService;
    private final ProductDetailsService productDetailsService;

    @Autowired
    public CreateSubscriptionSpecificPaymentAccountCommandHandler(Repository<PaymentAccount> repository,TaggedPricingService taggedPricingService,ProductDetailsService productDetailsService) {
        this.repository = repository;
        this.taggedPricingService=taggedPricingService;
        this.productDetailsService=productDetailsService;
    }

    @CommandHandler
    public void handle(CreateSubscriptionSpecificPaymentAccountCommand command) {
        PaymentAccount paymentAccount= new PaymentAccount(command.getSubscriberId(),command.getSubscriptionId(),command.getCreationDate());
        repository.add(paymentAccount);
    }
}
