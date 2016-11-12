package com.affaince.subscription.pricing.vo;

/**
 * Created by rbsavaliya on 21-08-2016.
 */
public class InterpolatedTotalSubscriptionsPerDay {

    private String productId;
    private double interpolatedTotalSubscriptionsPerDay;

    public InterpolatedTotalSubscriptionsPerDay(String productId, double interpolatedTotalSubscriptionsPerDay) {
        this.productId = productId;
        this.interpolatedTotalSubscriptionsPerDay = interpolatedTotalSubscriptionsPerDay;
    }

    public String getProductId() {
        return productId;
    }

    public double getInterpolatedTotalSubscriptionsPerDay() {
        return interpolatedTotalSubscriptionsPerDay;
    }
}
