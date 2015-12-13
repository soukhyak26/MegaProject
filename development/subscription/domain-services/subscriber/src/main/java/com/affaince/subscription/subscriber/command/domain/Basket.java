package com.affaince.subscription.subscriber.command.domain;

import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.subscriber.command.ItemDispatchStatus;
import com.affaince.subscription.subscriber.command.UpdateStatusAndDispatchDateCommand;
import com.affaince.subscription.subscriber.command.event.BasketCreatedEvent;
import com.affaince.subscription.subscriber.command.event.BasketDeletedEvent;
import com.affaince.subscription.subscriber.command.event.StatusAndDispatchDateUpdatedEvent;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by rbsavaliya on 02-10-2015.
 */
public class Basket {
    private String basketId;
    private List<DeliveryItem> deliveryItems;
    private LocalDate deliveryDate;
    private LocalDate dispatchDate;
    private DeliveryStatus status;

    public Basket(String basketId, List<DeliveryItem> deliveryItems, LocalDate deliveryDate, LocalDate dispatchDate, DeliveryStatus status) {
        this.basketId = basketId;
        this.deliveryItems = deliveryItems;
        this.deliveryDate = deliveryDate;
        this.dispatchDate = dispatchDate;
        this.status = status;
    }

    public Basket() {
    }

    public String getBasketId() {
        return basketId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }

    public List<DeliveryItem> getDeliveryItems() {
        return deliveryItems;
    }

    public void setDeliveryItems(List<DeliveryItem> deliveryItems) {
        this.deliveryItems = deliveryItems;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public LocalDate getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(LocalDate dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }
}
