package com.affaince.subscription.product.command.event;

/**
 * Created by mandar on 6/18/2017.
 */
public class ActiveForeacastsExpiredEvent {
    private String productId;
    public ActiveForeacastsExpiredEvent(String productId) {
        this.productId=productId;
    }

    public String getProductId() {
        return productId;
    }
}
