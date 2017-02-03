package com.affaince.subscription.business.command.event;

/**
 * Created by mandar on 03-02-2017.
 */
public class ProfitCreditedEvent {
    private Integer businessAccountId;
    private double amountToBeCredited;
    public ProfitCreditedEvent(Integer businessAccountId, double amountToBeCredited) {
        this.businessAccountId=businessAccountId;
        this.amountToBeCredited=amountToBeCredited;
    }

    public Integer getBusinessAccountId() {
        return businessAccountId;
    }

    public double getAmountToBeCredited() {
        return amountToBeCredited;
    }
}
