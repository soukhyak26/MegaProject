package com.affaince.subscription.consumerbasket.command.domain;

import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.consumerbasket.command.event.BasketCreatedEvent;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by rbsavaliya on 02-10-2015.
 */
public class Basket extends AbstractAnnotatedAggregateRoot<String> {
    @AggregateIdentifier
    private String basketId;
    private List<DeliveryItem> deliveryItems;
    private LocalDate deliveryDate;
    private LocalDate dispatchDate;
    private DeliveryStatus status;

    public Basket(String basketId, LocalDate deliveryDate, List<DeliveryItem> deliveryItems) {
        apply(new BasketCreatedEvent(basketId, deliveryItems, deliveryDate, null, DeliveryStatus.READYFORDELIVERY));
    }

    public Basket() {
    }

    @EventSourcingHandler
    public void on(BasketCreatedEvent event) {
        this.basketId = event.getBasketId();
        this.deliveryItems = event.getDeliveryItems();
        this.deliveryDate = event.getDeliveryDate();
        this.dispatchDate = event.getDispatchDate();
        this.status = event.getStatus();
    }
}
