package com.affaince.subscription.consumerbasket.command.domain;

import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.consumerbasket.command.ItemDispatchStatus;
import com.affaince.subscription.consumerbasket.command.UpdateStatusAndDispatchDateCommand;
import com.affaince.subscription.consumerbasket.command.event.BasketCreatedEvent;
import com.affaince.subscription.consumerbasket.command.event.BasketDeletedEvent;
import com.affaince.subscription.consumerbasket.command.event.StatusAndDispatchDateUpdatedEvent;
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

    @EventSourcingHandler
    public void on(StatusAndDispatchDateUpdatedEvent event) {
        this.basketId = event.getBasketId();
        this.dispatchDate = new LocalDate(event.getDispatchDate());
        this.status = DeliveryStatus.valueOf(event.getBasketDeliveryStatus());
        for (ItemDispatchStatus itemDispatchStatus : event.getItemDispatchStatuses()) {
            DeliveryItem deliveryItem = new DeliveryItem(itemDispatchStatus.getItemId(), null);
            deliveryItem = deliveryItems.get(deliveryItems.indexOf(deliveryItem));
            deliveryItem.setDeliveryStatus(DeliveryStatus.valueOf(itemDispatchStatus.getItemDeliveryStatus()));
        }
    }

    @EventSourcingHandler
    public void on(BasketDeletedEvent event) {
        this.basketId = event.getBasketId();
        this.status = event.getDeliveryStatus();
    }

    public void updateStatusAndDispatchDate(UpdateStatusAndDispatchDateCommand command) {
        apply(new StatusAndDispatchDateUpdatedEvent(this.basketId, command.getBasketDeliveryStatus(), command.getDispatchDate(),
                command.getItemDispatchStatuses()));
    }

    public void deleteBasket() {
        apply(new BasketDeletedEvent(this.basketId, DeliveryStatus.DELETED));
    }
}
