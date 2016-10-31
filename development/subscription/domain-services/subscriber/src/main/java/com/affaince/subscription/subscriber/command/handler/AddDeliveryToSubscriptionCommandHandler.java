package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.AddDeliveryToSubscriptionCommand;
import com.affaince.subscription.subscriber.command.domain.DeliveryChargesRule;
import com.affaince.subscription.subscriber.command.domain.Subscriber;
import com.affaince.subscription.subscriber.services.deliverychargesrule.DeliveryChargesRulesService;
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
    private final DeliveryChargesRulesService deliveryChargesRulesService;

    @Autowired
    public AddDeliveryToSubscriptionCommandHandler(Repository<Subscriber> subscriberRepository,
                                                   DeliveryChargesRulesService deliveryChargesRulesService) {
        this.subscriberRepository = subscriberRepository;
        this.deliveryChargesRulesService = deliveryChargesRulesService;
    }

    @CommandHandler
    public void handle(AddDeliveryToSubscriptionCommand command) {
        final Subscriber subscriber = subscriberRepository.load(command.getSubscriberId());
        final DeliveryChargesRule deliveryChargesRule = deliveryChargesRulesService.findActiveDeliveryChargesRule();
        subscriber.addDelivery(command.getDeliveryId(), command.getDeliveryDate(),
                command.getDeliveryItems(), deliveryChargesRule);
    }
}