package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.ConfirmSubscriptionCommand;
import com.affaince.subscription.subscriber.domain.Subscriber;
import com.affaince.subscription.subscriber.services.benefit.context.BenefitExecutionContext;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rsavaliya on 17/1/16.
 */
@Component
public class ConfirmSubscriptionCommandHandler {
    private final Repository<Subscriber> subscriberRepository;
    private final BenefitExecutionContext benefitExecutionContext;

    @Autowired
    public ConfirmSubscriptionCommandHandler(Repository<Subscriber> subscriberRepository, BenefitExecutionContext benefitExecutionContext) {
        this.subscriberRepository = subscriberRepository;
        this.benefitExecutionContext = benefitExecutionContext;
    }

    @CommandHandler
    public void handler(ConfirmSubscriptionCommand command) {
        final Subscriber subscriber = subscriberRepository.load(command.getSubscriberId());
        subscriber.confirmSubscription(
                command.getDeliveryChargesRule(), command.getLatestPriceBucketMap(), benefitExecutionContext);
    }
}
