package com.affaince.subscription.business.command.event;

/**
 * Created by mandar on 16-01-2017.
 */
public class OthersAccountDebitedEvent {
    private  final Integer businessAccountId;
    private final double amountToDebit;
    public OthersAccountDebitedEvent(Integer businessAccountId, double amountToDebit) {
        this.amountToDebit=amountToDebit;
        this.businessAccountId=businessAccountId;
    }

    public Integer getBusinessAccountId() {
        return businessAccountId;
    }

    public double getAmountToDebit() {
        return amountToDebit;
    }
}
