package com.affaince.subscription.subscriber.web.request;

/**
 * Created by rbsavaliya on 04-10-2015.
 */
public class BasketDispatchRequest {

    private int basketDeliveryStatus;
    private ItemStatusRequest[] itemStatusRequest;
    private String dispatchDate;

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

    public String getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(String dispatchDate) {
        this.dispatchDate = dispatchDate;
    }
}
