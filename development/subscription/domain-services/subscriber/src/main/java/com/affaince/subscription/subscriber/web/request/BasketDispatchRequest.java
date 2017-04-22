package com.affaince.subscription.subscriber.web.request;

import org.joda.time.LocalDate;

/**
 * Created by rbsavaliya on 04-10-2015.
 */
public class BasketDispatchRequest {

    private int basketDeliveryStatus;
    private ItemStatusRequest[] itemStatusRequest;
    private LocalDate dispatchDate;
    private String reasonCode;

    public int getBasketDeliveryStatus() {
        return basketDeliveryStatus;
    }

    public void setBasketDeliveryStatus(int basketDeliveryStatus) {
        this.basketDeliveryStatus = basketDeliveryStatus;
    }

    public ItemStatusRequest[] getItemStatusRequest() {
        return itemStatusRequest;
    }

    public void setItemStatusRequest(ItemStatusRequest[] itemStatusRequest) {
        this.itemStatusRequest = itemStatusRequest;
    }

    public LocalDate getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(LocalDate dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }
}
