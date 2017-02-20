package com.affaince.subscription.common.type;

import com.affaince.subscription.common.deserializer.EntityStatusDeserializer;
import com.affaince.subscription.common.serializer.EntityStatusSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by mandark on 28-01-2016.
 */
@JsonSerialize(using = EntityStatusSerializer.class)
@JsonDeserialize(using = EntityStatusDeserializer.class)
public enum EntityStatus {
    CREATED(0),ACTIVE(1),EXPIRED(2), ;
    private int statusCode;

    EntityStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    public static EntityStatus valueOf(int value) {
        for (EntityStatus entityStatus : EntityStatus.values()) {
            if (entityStatus.getStatusCode() == value) {
                return entityStatus;
            }
        }
        throw new IllegalArgumentException();
    }

    public int getStatusCode () {
        return statusCode;
    }
}
