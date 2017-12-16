package com.affaince.subscription.product.event;

/**
 * Created by mandar on 6/18/2017.
 */
public class ActivePseudoActualsExpiredEvent {
    private String productId;
    public ActivePseudoActualsExpiredEvent(String productId) {
        this.productId=productId;
    }

    public String getProductId() {
        return productId;
    }
}
