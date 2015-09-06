package com.affaince.subscription.consumerbasket.query.view;

/**
 * Created by rbsavaliya on 23-08-2015.
 */
public enum ConsumerBasketStatus {

    CREATED(0), CONFIRMED(1);

    private int statusCode;

    ConsumerBasketStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
