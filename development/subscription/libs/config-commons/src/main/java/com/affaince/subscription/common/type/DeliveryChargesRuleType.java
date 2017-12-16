package com.affaince.subscription.common.type;

/**
 * Created by rsavaliya on 27/2/16.
 */
public enum DeliveryChargesRuleType {
    CHARGES_ON_DELIVERY_WEIGHT("0");

    private String deliveryChargesRuleTypeCode;

    DeliveryChargesRuleType(String deliveryChargesRuleTypeCode) {
        this.deliveryChargesRuleTypeCode = deliveryChargesRuleTypeCode;
    }

    public static DeliveryChargesRuleType valueOf(int deliveryChargesRuleTypeCode) {
        switch (deliveryChargesRuleTypeCode) {
            case 0:
                return CHARGES_ON_DELIVERY_WEIGHT;
            default:
                return CHARGES_ON_DELIVERY_WEIGHT;
        }
    }

    public String getDeliveryChargesRuleTypeCode() {
        return deliveryChargesRuleTypeCode;
    }
}