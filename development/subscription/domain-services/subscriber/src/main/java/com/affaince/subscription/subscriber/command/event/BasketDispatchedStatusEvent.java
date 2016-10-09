package com.affaince.subscription.subscriber.command.event;

import java.util.Date;

/**
 * Created by mandark on 21-08-2015.
 */
public class BasketDispatchedStatusEvent {
    private String subscriberId;
    private Date dispatchDate;
    private int dispactchStatusCode;
    private int reasonCode;

    public BasketDispatchedStatusEvent() {
    }

    public String getSubscriberId() {
        return this.subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
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
