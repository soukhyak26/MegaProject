package com.affaince.subscription.subscriber.web.request;

/**
 * Created by rsavaliya on 8/3/17.
 */
public class AddSubscriptionRequest {

    private BasketItemRequest [] basketItemRequests;

    public BasketItemRequest[] getBasketItemRequests() {
        return basketItemRequests;
    }

    public void setBasketItemRequests(BasketItemRequest[] basketItemRequests) {
        this.basketItemRequests = basketItemRequests;
    }
}
