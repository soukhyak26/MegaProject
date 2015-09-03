package com.affaince.subscription.common.type;

/**
 * Created by rbsavaliya on 31-08-2015.
 */
public enum NetWorthSubscriberStatus {

    NORMAL (0), PREMIUM (1), GOLD (2);

    private int subscriberSatusCode;

    NetWorthSubscriberStatus(int subscriberSatusCode) {
        this.subscriberSatusCode = subscriberSatusCode;
    }

    public int getSubscriberStatusCode() {
        return subscriberSatusCode;
    }

    public static NetWorthSubscriberStatus valueOf (int subscriberSatusCode) {
        switch (subscriberSatusCode) {
            case 0:
                return NORMAL;
            case 1:
                return PREMIUM;
            case 2:
                return GOLD;
            default:
                return NORMAL;
        }
    }
}
