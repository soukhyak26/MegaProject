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

    public static <R> DeliveryExpression create(String s) {
        DeliveryExpression deliveryExpression = new DeliveryExpression(
                (s.charAt(3) == 'N')? TotalDeliveryBase.N: TotalDeliveryBase.REMAINING_N,
                Integer.parseInt(s.charAt(0) + ""),
                Integer.parseInt(s.charAt(2) + "")
        );
        return deliveryExpression;
    }
}
