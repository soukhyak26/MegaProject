package com.affaince.subscription.common.type;

/**
 * Created by rbsavaliya on 23-08-2015.
 */
public enum ConsumerBasketActivationStatus {
    CREATED(0), ACTIVATED(1), EXPIRED(2);

    private int statusCode;

    ConsumerBasketActivationStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public static ConsumerBasketActivationStatus valueOf(int statusCode) {
        switch (statusCode) {
            case 0:
                return CREATED;
            case 1:
                return ACTIVATED;
            case 2:
                return EXPIRED;
            default:
                return CREATED;
        }
    }
}
