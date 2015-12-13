package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.subscriber.command.CreateBasketCommand;
import com.affaince.subscription.subscriber.command.domain.Basket;
import com.affaince.subscription.subscriber.command.domain.DeliveryItem;
import com.affaince.subscription.subscriber.command.domain.Subscriber;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rbsavaliya on 02-10-2015.
 */
@Component
public class CreateBasketCommandHandler {

    private final Repository<Subscriber> subscriberRepository;

    @Autowired
    public CreateBasketCommandHandler(Repository<Subscriber> subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    @CommandHandler
    public void on(CreateBasketCommand command) {
        List<DeliveryItem> deliveryItems = new ArrayList<>(command.getDeliveryItems().size());
        deliveryItems.addAll(command.getDeliveryItems().stream().map(deliveryItemId -> new DeliveryItem(deliveryItemId, DeliveryStatus.READYFORDELIVERY)).collect(Collectors.toList()));
        Basket basket = new Basket(command.getBasketId(), deliveryItems, new LocalDate(command.getDeliveryDate()), null, DeliveryStatus.READYFORDELIVERY);
        Subscriber subscriber = subscriberRepository.load(command.getSubscriberId());
        subscriber.addBasket(basket);
    }
}
