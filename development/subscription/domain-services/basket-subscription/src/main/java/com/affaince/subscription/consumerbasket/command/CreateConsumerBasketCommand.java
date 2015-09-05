package com.affaince.subscription.consumerbasket.command;

/**
 * Created by rbsavaliya on 09-08-2015.
 */
public class CreateConsumerBasketCommand {
    private String basketId;
    private String userId;

    public CreateConsumerBasketCommand(String basketId, String userId) {
        this.basketId = basketId;
        this.userId = userId;
    }

    public String getBasketId() {
        return basketId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
