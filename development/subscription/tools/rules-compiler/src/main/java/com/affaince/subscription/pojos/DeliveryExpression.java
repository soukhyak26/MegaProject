package com.affaince.subscription.pojos;

import com.affaince.subscription.common.type.TotalDeliveryBase;

/**
 * Created by rahul on 15/7/17.
 */
public class DeliveryExpression {

    private TotalDeliveryBase totalDeliveryBase;
    private int dividend;
    private int divisor;

    public DeliveryExpression(TotalDeliveryBase totalDeliveryBase, int dividend, int divisor) {
        this.totalDeliveryBase = totalDeliveryBase;
        this.dividend = dividend;
        this.divisor = divisor;
    }

    public DeliveryExpression() {
    }

    public TotalDeliveryBase getTotalDeliveryBase() {
        return totalDeliveryBase;
    }

    public int getDividend() {
        return dividend;
    }

    public int getDivisor() {
        return divisor;
    }
}
