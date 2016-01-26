package com.affaince.subscription.common.type;

/**
 * Created by mandark on 26-01-2016.
 */
public enum SensitivityCharacteristic {
    NONE(0),
    ELECTRICITY_CONSUMPTION(1),
    STORAGE_SPACE_CONSUMPTION(2);

    SensitivityCharacteristic(int i) {
        this.characteristicNumber = i;
    }

    private int characteristicNumber;
}
