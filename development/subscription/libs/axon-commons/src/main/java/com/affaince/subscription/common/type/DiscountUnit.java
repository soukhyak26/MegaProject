package com.affaince.subscription.common.type;

/**
 * Created by rbsavaliya on 29-08-2015.
 */
public enum DiscountUnit {
    CURRENCY(0), PERCENTAGE(1), ITEM(2), CASHBACK (3), REWARDPOINTS (4);

    private int discountCode;

    DiscountUnit(int discountCode) {
        this.discountCode = discountCode;
    }

    public static DiscountUnit valueOf(int discountCode) {
        switch (discountCode) {
            case 0:
                return CURRENCY;
            case 1:
                return PERCENTAGE;
            case 2:
                return ITEM;
            case 3:
                return CASHBACK;
            case 4:
                return REWARDPOINTS;
            default:
                return PERCENTAGE;
        }
    }

    public int getDiscountCode() {
        return discountCode;
    }
}
