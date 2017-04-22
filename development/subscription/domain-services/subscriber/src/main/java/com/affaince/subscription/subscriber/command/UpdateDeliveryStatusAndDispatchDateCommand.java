package com.affaince.subscription.subscriber.command;

import com.affaince.subscription.command.ItemDispatchStatus;
import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.type.ReasonCode;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by rbsavaliya on 04-10-2015.
 */
public class UpdateDeliveryStatusAndDispatchDateCommand {
    @TargetAggregateIdentifier
    private String subscriberId;
    private String subscriptionId;
    private String deliveryId;
    private DeliveryStatus deliveryStatus;
    private LocalDate dispatchDate;
    private List<ItemDispatchStatus> itemDispatchStatuses;
    private ReasonCode reasonCode;

    public UpdateDeliveryStatusAndDispatchDateCommand() {
    }

    public UpdateDeliveryStatusAndDispatchDateCommand(String subscriberId, String subscriptionId, String deliveryId, DeliveryStatus deliveryStatus, LocalDate dispatchDate, List<ItemDispatchStatus> itemDispatchStatuses, ReasonCode reasonCode) {
        this.subscriberId = subscriberId;
        this.subscriptionId = subscriptionId;
        this.deliveryId = deliveryId;
        this.deliveryStatus = deliveryStatus;
        this.dispatchDate = dispatchDate;
        this.itemDispatchStatuses = itemDispatchStatuses;
        this.reasonCode = reasonCode;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public LocalDate getDispatchDate() {
        return dispatchDate;
    }

    public List<ItemDispatchStatus> getItemDispatchStatuses() {
        return itemDispatchStatuses;
    }

    public ReasonCode getReasonCode() {
        return reasonCode;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }
}
