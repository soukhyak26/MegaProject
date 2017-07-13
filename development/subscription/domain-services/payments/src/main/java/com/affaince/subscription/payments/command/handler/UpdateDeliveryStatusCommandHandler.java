package com.affaince.subscription.payments.command.handler;

import com.affaince.subscription.payments.command.UpdateDeliveryStatusCommand;
import com.affaince.subscription.payments.command.domain.PaymentAccount;
import com.affaince.subscription.payments.service.ProductDetailsService;
import com.affaince.subscription.payments.service.TaggedPricingService;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 7/13/2017.
 */
@Component
public class UpdateDeliveryStatusCommandHandler {
    private final Repository<PaymentAccount> paymentAccountRepository;
    private final ProductDetailsService productDetailsService;
    private final TaggedPricingService taggedPricingService;

    @Autowired
    public UpdateDeliveryStatusCommandHandler(Repository<PaymentAccount> paymentAccountRepository,ProductDetailsService productDetailsService,TaggedPricingService taggedPricingService) {
        this.paymentAccountRepository = paymentAccountRepository;
        this.productDetailsService=productDetailsService;
        this.taggedPricingService=taggedPricingService;
    }

    public void handle(UpdateDeliveryStatusCommand command){
        PaymentAccount paymentAccount=paymentAccountRepository.load(command.getSubscriptionId());
        paymentAccount.updateDeliveryStatus(command,productDetailsService,taggedPricingService);
    }
}
