package com.affaince.subscription.common.type;

/**
 * Created by rbsavaliya on 02-10-2015.
 */
public enum DeliveryStatus {
    CREATED (0), READYFORDELIVERY(1), DELIVERED(2), FAILURE(3), PARTIAL(4), DELETED(5);

    private int deliveryStatusCode;

    DeliveryStatus(int deliveryStatusCode) {
        this.deliveryStatusCode = deliveryStatusCode;
    }

    public static DeliveryStatus valueOf(int deliveryStatusCode) {
        switch (deliveryStatusCode) {
            case 0:
                return CREATED;
            case 1:
                return READYFORDELIVERY;
            case 2:
                return DELIVERED;
            case 3:
                return FAILURE;
            case 4:
                return PARTIAL;
            case 5:
                return DELETED;
            default:
                return READYFORDELIVERY;
        }
    }

    public int getDeliveryStatusCode() {
        return deliveryStatusCode;
    }
}
