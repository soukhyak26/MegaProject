package com.affaince.subscription.pricing.vo;

/**
 * Created by mandark on 28-02-2016.
 */
public class FunctionCoefficients {
    private final double intercept;
    private final double slope;
    private final CoefficientsType type;

    public FunctionCoefficients(double intercept, double slope,CoefficientsType type) {
        this.intercept = intercept;
        this.slope = slope;
        this.type=type;
    }

    public double getIntercept() {
        return this.intercept;
    }

    public double getSlope() {
        return this.slope;
    }

    public CoefficientsType getType() {
        return this.type;
    }
}
