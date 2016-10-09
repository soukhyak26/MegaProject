package com.affaince.subscription.common.type;

import com.affaince.subscription.common.deserializer.SensitivityCharacteristicDeserializer;
import com.affaince.subscription.common.serializer.SensitivityCharacteristicSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by mandark on 26-01-2016.
 */
@JsonSerialize(using = SensitivityCharacteristicSerializer.class)
@JsonDeserialize(using = SensitivityCharacteristicDeserializer.class)
public enum SensitivityCharacteristic {
    NONE(0),
    ELECTRICITY_CONSUMPTION(1),
    STORAGE_SPACE_CONSUMPTION(2);

    private int characteristicNumber;

    SensitivityCharacteristic(int i) {
        this.characteristicNumber = i;
    }

    public static SensitivityCharacteristic valueOf(int value) {
        for (SensitivityCharacteristic r : SensitivityCharacteristic.values()) {
            if (r.getCharacteristicNumber() == value) {
                return r;
            }
        }
        throw new IllegalArgumentException();
    }

    public int getCharacteristicNumber() {
        return characteristicNumber;
    }
}
