package com.affaince.subscription.testdata.generator;

import com.affaince.subscription.common.type.Period;

import java.util.List;

/**
 * Created by rbsavaliya on 09-08-2015.
 */
public class SubscriptionItem {
    private List <BasketItemRequest> basketItemRequests;

    public List<BasketItemRequest> getBasketItemRequests() {
        return basketItemRequests;
    }

    public void setBasketItemRequests(List<BasketItemRequest> basketItemRequests) {
        this.basketItemRequests = basketItemRequests;
    }
}
