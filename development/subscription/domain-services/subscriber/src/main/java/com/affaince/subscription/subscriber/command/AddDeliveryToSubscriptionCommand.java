package com.affaince.subscription.subscriber.command;

import com.affaince.subscription.subscriber.command.domain.DeliveryChargesRule;
import com.affaince.subscription.subscriber.web.request.DeliveryItem;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by rbsavaliya on 23-10-2016.
 */
public class AddDeliveryToSubscriptionCommand {
    @TargetAggregateIdentifier
    private String subscriberId;
    private String deliveryId;
    private LocalDate deliveryDate;
    private List<DeliveryItem> deliveryItems;
    private DeliveryChargesRule deliveryChargesRule;

    public AddDeliveryToSubscriptionCommand(String subscriberId, String deliveryId, LocalDate deliveryDate, List<DeliveryItem> deliveryItems, DeliveryChargesRule deliveryChargesRule) {
        this.subscriberId = subscriberId;
        this.deliveryId = deliveryId;
        this.deliveryDate = deliveryDate;
        this.deliveryItems = deliveryItems;
        this.deliveryChargesRule = deliveryChargesRule;
    }

    public AddDeliveryToSubscriptionCommand() {
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public List<DeliveryItem> getDeliveryItems() {
        return deliveryItems;
    }

    public DeliveryChargesRule getDeliveryChargesRule() {
        return deliveryChargesRule;
    }
}
