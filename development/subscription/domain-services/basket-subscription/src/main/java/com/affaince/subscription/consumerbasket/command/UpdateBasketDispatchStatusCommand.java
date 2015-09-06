package com.affaince.subscription.consumerbasket.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.Date;

/**
 * Created by mandark on 30-08-2015.
 */
public class UpdateBasketDispatchStatusCommand {
    @TargetAggregateIdentifier
    private String basketId;
    private Date dispatchDate;
    private int dispactchStatusCode;
    private int reasonCode;

    public UpdateBasketDispatchStatusCommand() {
    }

    public UpdateBasketDispatchStatusCommand(String basketId, Date dispatchDate, int dispactchStatusCode, int reasonCode) {
        this.basketId = basketId;
        this.dispatchDate = dispatchDate;
        this.dispactchStatusCode = dispactchStatusCode;
        this.reasonCode = reasonCode;
    }

    public String getBasketId() {
        return this.basketId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }

    public Date getDispatchDate() {
        return this.dispatchDate;
    }

    public void setDispatchDate(Date dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public int getDispactchStatusCode() {
        return this.dispactchStatusCode;
    }

    public void setDispactchStatusCode(int dispactchStatusCode) {
        this.dispactchStatusCode = dispactchStatusCode;
    }

    public int getReasonCode() {
        return this.reasonCode;
    }

    public void setReasonCode(int reasonCode) {
        this.reasonCode = reasonCode;
    }
}
