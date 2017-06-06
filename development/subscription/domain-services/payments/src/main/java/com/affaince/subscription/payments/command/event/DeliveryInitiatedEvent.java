package com.affaince.subscription.payments.command.event;

import com.affaince.subscription.payments.command.accounting.DeliveryCostAccount;

/**
 * Created by anayonkar on 24/8/16.
 */
public class DeliveryInitiatedEvent {
    private String subscriberId;
    private String subscriptionId;
    private String deliveryId;
    private DeliveryCostAccount newDeliveryCostAccount;
    public DeliveryInitiatedEvent(String subscriberId,String subscriptionId,String deliveryId,DeliveryCostAccount newDeliveryCostAccount ) {
        this.subscriberId=subscriberId;
        this.subscriptionId = subscriptionId;
        this.deliveryId = deliveryId;
        this.newDeliveryCostAccount= newDeliveryCostAccount;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public DeliveryCostAccount getNewDeliveryCostAccount() {
        return newDeliveryCostAccount;
    }
}
