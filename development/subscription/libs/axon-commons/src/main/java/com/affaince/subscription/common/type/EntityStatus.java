package com.affaince.subscription.common.type;

/**
 * Created by mandark on 28-01-2016.
 */
public enum EntityStatus {
    CREATED(0),ACTIVE(1),EXPIRED(2), ;
    private int statusCode;

    EntityStatus(int statusCode) {
        this.statusCode = statusCode;
    }
}
