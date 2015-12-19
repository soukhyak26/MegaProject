package com.affaince.subscription.subscriber.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.List;

/**
 * Created by rbsavaliya on 04-10-2015.
 */
public class UpdateStatusAndDispatchDateCommand {
    @TargetAggregateIdentifier
    private String subscriberId;
    private String basketId;
    private int basketDeliveryStatus;
    private String dispatchDate;
    private List<ItemDispatchStatus> itemDispatchStatuses;

    public UpdateStatusAndDispatchDateCommand(String subscriberId, String basketId, int basketDeliveryStatus, String dispatchDate, List<ItemDispatchStatus> itemDispatchStatuses) {
        this.subscriberId = subscriberId;
        this.basketId = basketId;
        this.basketDeliveryStatus = basketDeliveryStatus;
        this.dispatchDate = dispatchDate;
        this.itemDispatchStatuses = itemDispatchStatuses;
    }

    public UpdateStatusAndDispatchDateCommand() {
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getBasketId() {
        return basketId;
    }

    public int getBasketDeliveryStatus() {
        return basketDeliveryStatus;
    }

    public String getDispatchDate() {
        return dispatchDate;
    }

    public List<ItemDispatchStatus> getItemDispatchStatuses() {
        return itemDispatchStatuses;
    }
}