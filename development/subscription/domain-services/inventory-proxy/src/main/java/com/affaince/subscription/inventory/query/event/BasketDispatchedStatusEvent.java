package com.affaince.subscription.inventory.query.event;

import org.joda.time.LocalDate;

/**
 * Created by mandark on 21-08-2015.
 */
public class BasketDispatchedStatusEvent {
    private String basketId;
    private LocalDate dispatchDate;
    private int dispactchStatusCode;
    private int reasonCode;

    public BasketDispatchedStatusEvent(String basketId, LocalDate dispatchDate, int dispactchStatusCode, int reasonCode) {
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

    public LocalDate getDispatchDate() {
        return this.dispatchDate;
    }

    public void setDispatchDate(LocalDate dispatchDate) {
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
