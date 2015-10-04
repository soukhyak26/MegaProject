package com.affaince.subscription.common.type;

/**
 * Created by rbsavaliya on 02-10-2015.
 */
public enum DeliveryStatus {
    READYFORDELIVERY (0), DELIVERED(1), FAILURE(2), PARTIAL(3);

    private int deliveryStatusCode;

    DeliveryStatus(int deliveryStatusCode) {
        this.deliveryStatusCode = deliveryStatusCode;
    }

    public static DeliveryStatus valueOf(int deliveryStatusCode) {
        switch (deliveryStatusCode) {
            case 0:
                return READYFORDELIVERY;
            case 1:
                return DELIVERED;
            case 2:
                return FAILURE;
            case 3:
                return PARTIAL;
            default:
                return READYFORDELIVERY;
        }
    }

    public int getDeliveryStatusCode() {
        return deliveryStatusCode;
    }
}
