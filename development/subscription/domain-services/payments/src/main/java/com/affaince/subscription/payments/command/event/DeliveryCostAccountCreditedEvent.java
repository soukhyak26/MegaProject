package com.affaince.subscription.payments.command.event;

public class DeliveryCostAccountCreditedEvent extends CreditedEvent {
    private int sequence;
    public DeliveryCostAccountCreditedEvent() {
    }

    public DeliveryCostAccountCreditedEvent(String id, int sequence,double amountToCredit) {
        super(id, amountToCredit);
        this.sequence=sequence;
    }

    public int getSequence() {
        return sequence;
    }
}
