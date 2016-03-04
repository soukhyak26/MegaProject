package com.affaince.subscription.pricing.vo;

/**
 * Created by mandark on 28-02-2016.
 */
public enum CoefficientsType {
    DEMAND_FUNCTION_COEFFICIENT(0), COST_FUNCTION_COEFFICIENT(1);

    private int typeCode;
    CoefficientsType(int typeCode) {
        this.typeCode=typeCode;
    }
}
