package com.affaince.subscription.subscriber.vo;

/**
 * Created by mandar on 9/30/2017.
 */
public class SubscriptionValueRange {
    private double minimumValue;
    private double maximumValue;

    public SubscriptionValueRange(double minimumValue, double maximumValue) {
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
    }

    public double getMinimumValue() {
        return minimumValue;
    }

    public double getMaximumValue() {
        return maximumValue;
    }
}
