package com.affaince.subscription.subscriber.command;

import com.affaince.subscription.command.ItemDispatchStatus;
import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.type.ReasonCode;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.List;

/**
 * Created by rbsavaliya on 04-10-2015.
 */
public class UpdateDeliveryStatusAndDispatchDateCommand {
    @TargetAggregateIdentifier
    private String subscriberId;
    private String deliveryId;
    private DeliveryStatus deliveryStatus;
    private String dispatchDate;
    private List<ItemDispatchStatus> itemDispatchStatuses;
    private ReasonCode reasonCode;

    public UpdateDeliveryStatusAndDispatchDateCommand() {
    }

    public UpdateDeliveryStatusAndDispatchDateCommand(String subscriberId, String deliveryId, DeliveryStatus deliveryStatus, String dispatchDate, List<ItemDispatchStatus> itemDispatchStatuses, ReasonCode reasonCode) {
        this.subscriberId = subscriberId;
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

    public String getDispatchDate() {
        return dispatchDate;
    }

    public List<ItemDispatchStatus> getItemDispatchStatuses() {
        return itemDispatchStatuses;
    }

    public ReasonCode getReasonCode() {
        return reasonCode;
    }
}
