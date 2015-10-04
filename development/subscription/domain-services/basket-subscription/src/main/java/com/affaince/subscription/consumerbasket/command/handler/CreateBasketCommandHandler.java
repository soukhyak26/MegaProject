package com.affaince.subscription.consumerbasket.command.handler;

import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.consumerbasket.command.CreateBasketCommand;
import com.affaince.subscription.consumerbasket.command.domain.Basket;
import com.affaince.subscription.consumerbasket.command.domain.DeliveryItem;
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

    private final Repository<Basket> basketRepository;

    @Autowired
    public CreateBasketCommandHandler(Repository<Basket> basketRepository) {
        this.basketRepository = basketRepository;
    }

    @CommandHandler
    public void on(CreateBasketCommand command) {
        List<DeliveryItem> deliveryItems = new ArrayList<>(command.getDeliveryItems().size());
        deliveryItems.addAll(command.getDeliveryItems().stream().map(deliveryItemId -> new DeliveryItem(deliveryItemId, DeliveryStatus.READYFORDELIVERY)).collect(Collectors.toList()));
        Basket basket = new Basket(command.getBasketId(), new LocalDate(command.getDeliveryDate()), deliveryItems);
        basketRepository.add(basket);
    }
}
