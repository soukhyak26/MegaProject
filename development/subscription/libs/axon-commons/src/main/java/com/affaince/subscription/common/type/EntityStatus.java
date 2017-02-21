package com.affaince.subscription.common.type;

import com.affaince.subscription.common.deserializer.EntityStatusDeserializer;
import com.affaince.subscription.common.serializer.EntityStatusSerializer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by mandark on 28-01-2016.
 */
public enum EntityStatus {
    CREATED(0),ACTIVE(1),EXPIRED(2), ;
    private final int statusCode;

    EntityStatus(final int statusCode) {
        this.statusCode = statusCode;
    }
    @JsonCreator
    public static EntityStatus valueOf(int value) {
        for (EntityStatus entityStatus : EntityStatus.values()) {
            if (entityStatus.getStatusCode() == value) {
                return entityStatus;
            }
        }
        throw new IllegalArgumentException();
    }
    @JsonValue
    public int getStatusCode () {
        return statusCode;
    }
}
