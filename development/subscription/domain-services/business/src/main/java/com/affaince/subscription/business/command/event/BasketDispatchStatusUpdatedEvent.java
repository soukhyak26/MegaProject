package com.affaince.subscription.business.command.event;

import java.util.Date;

/**
 * Created by anayonkar on 8/5/16.
 */
public class BasketDispatchStatusUpdatedEvent {
    private String basketId;
    private Date dispatchDate;
    private int dispactchStatusCode;
    private int reasonCode;

    public BasketDispatchStatusUpdatedEvent(String basketId, Date dispatchDate, int dispactchStatusCode, int reasonCode) {
        this.basketId = basketId;
        this.dispatchDate = dispatchDate;
        this.dispactchStatusCode = dispactchStatusCode;
        this.reasonCode = reasonCode;
    }

    public String getBasketId() {
        return basketId;
    }

    public Date getDispatchDate() {
        return dispatchDate;
    }

    public int getDispactchStatusCode() {
        return dispactchStatusCode;
    }

    public int getReasonCode() {
        return reasonCode;
    }
}
