package com.affaince.subscription.business.command;

import java.util.Date;

/**
 * Created by anayonkar on 8/5/16.
 */
public class BasketDispatchStatusUpdatedCommand {
    private String basketId;
    private Date dispatchDate;
    private int dispactchStatusCode;
    private int reasonCode;

    public BasketDispatchStatusUpdatedCommand(String basketId, Date dispatchDate, int dispactchStatusCode, int reasonCode) {
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
