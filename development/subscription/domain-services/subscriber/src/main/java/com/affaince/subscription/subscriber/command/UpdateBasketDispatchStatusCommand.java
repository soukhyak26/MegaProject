package com.affaince.subscription.subscriber.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.Date;

/**
 * Created by mandark on 30-08-2015.
 */
public class UpdateBasketDispatchStatusCommand {
    @TargetAggregateIdentifier
    private String basketId;
    private Date dispatchDate;
    private int dispatchStatusCode;
    private int reasonCode;

    public UpdateBasketDispatchStatusCommand() {
    }

    public UpdateBasketDispatchStatusCommand(String basketId, Date dispatchDate, int dispatchStatusCode, int reasonCode) {
        this.basketId = basketId;
        this.dispatchDate = dispatchDate;
        this.dispatchStatusCode = dispatchStatusCode;
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

    public int getDispatchStatusCode() {
        return this.dispatchStatusCode;
    }

    public void setDispatchStatusCode(int dispatchStatusCode) {
        this.dispatchStatusCode = dispatchStatusCode;
    }

    public int getReasonCode() {
        return this.reasonCode;
    }

    public void setReasonCode(int reasonCode) {
        this.reasonCode = reasonCode;
    }
}