package com.affaince.subscription.inventory.command.domain;

import org.joda.time.LocalDate;

/**
 * Created by mandark on 22-08-2015.
 */
public class SubscriptionBasketStatus {
    private LocalDate dispatchDate;
    private int dispatchStatus;
    private int reasonCode;

    public LocalDate getDispatchDate() {
        return this.dispatchDate;
    }

    public void setDispatchDate(LocalDate dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public int getDispatchStatus() {
        return this.dispatchStatus;
    }

    public void setDispatchStatus(int dispatchStatus) {
        this.dispatchStatus = dispatchStatus;
    }

    public int getReasonCode() {
        return this.reasonCode;
    }

    public void setReasonCode(int reasonCode) {
        this.reasonCode = reasonCode;
    }
}
