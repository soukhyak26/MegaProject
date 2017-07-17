package com.affaince.subscription.payments.command.event;

public class DeliveryCostAccountCreditedEvent extends CreditedEvent {
    private String subscriberId;
    private String subscriptionId;
    private int sequence;
    public DeliveryCostAccountCreditedEvent() {
    }

    public DeliveryCostAccountCreditedEvent(String subscriberId,String subscriptionId,String id, int sequence,double amountToCredit) {
        super(id, amountToCredit);
        this.subscriberId=subscriberId;
        this.subscriptionId=subscriptionId;
        this.sequence=sequence;
    }

    public int getSequence() {
        return sequence;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }
}
