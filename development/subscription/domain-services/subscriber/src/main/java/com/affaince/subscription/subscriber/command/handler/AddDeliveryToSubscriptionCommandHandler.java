package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.AddDeliveryToSubscriptionCommand;
import com.affaince.subscription.subscriber.command.domain.Subscriber;
import com.affaince.subscription.subscriber.services.benefit.context.BenefitExecutionContext;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 23-10-2016.
 */
@Component
public class AddDeliveryToSubscriptionCommandHandler {

    private final Repository<Subscriber> subscriberRepository;
    private final BenefitExecutionContext benefitExecutionContext;

    @Autowired
    public AddDeliveryToSubscriptionCommandHandler(Repository<Subscriber> subscriberRepository, BenefitExecutionContext benefitExecutionContext) {
        this.subscriberRepository = subscriberRepository;
        this.benefitExecutionContext = benefitExecutionContext;
    }

    @CommandHandler
    public void handle(AddDeliveryToSubscriptionCommand command) {
        final Subscriber subscriber = subscriberRepository.load(command.getSubscriberId());
        subscriber.addDelivery(command.getDeliveryId(), command.getDeliveryDate(),
                command.getDeliveryItems(), command.getDeliveryChargesRule(), benefitExecutionContext);
    }
}
