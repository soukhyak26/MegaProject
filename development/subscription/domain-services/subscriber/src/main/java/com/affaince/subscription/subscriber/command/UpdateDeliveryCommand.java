package com.affaince.subscription.subscriber.command;

import com.affaince.subscription.subscriber.command.domain.DeliveryChargesRule;
import com.affaince.subscription.subscriber.web.request.DeliveryItem;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by rbsavaliya on 25-10-2016.
 */
public class UpdateDeliveryCommand {
    @TargetAggregateIdentifier
    private String subscriberId;
    private String subscriptionId;
    private String deliveryId;
    private LocalDate deliveryDate;
    private List<DeliveryItem> deliveryItems;
    private DeliveryChargesRule deliveryChargesRule;

    public UpdateDeliveryCommand(String subscriberId, String subscriptionId, String deliveryId, LocalDate deliveryDate, List<DeliveryItem> deliveryItems, DeliveryChargesRule deliveryChargesRule) {
        this.subscriberId = subscriberId;
        this.subscriptionId = subscriptionId;
        this.deliveryId = deliveryId;
        this.deliveryDate = deliveryDate;
        this.deliveryItems = deliveryItems;
        this.deliveryChargesRule = deliveryChargesRule;
    }

    public UpdateDeliveryCommand() {
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
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
