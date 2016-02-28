package com.affaince.subscription.pricing.vo;

/**
 * Created by mandark on 28-02-2016.
 */
public enum CoefficientsType {
    DEMAND_FUNCTION_COEFFICIENT(0),COST_FUNCTON_COEFFICIENT(1);

    CoefficientsType(int typeCode) {
        this.typeCode=typeCode;
    }
    private int typeCode;
}
