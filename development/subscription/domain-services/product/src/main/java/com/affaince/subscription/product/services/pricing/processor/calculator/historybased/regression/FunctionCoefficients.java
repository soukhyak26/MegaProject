package com.affaince.subscription.product.services.pricing.processor.calculator.historybased.regression;

/**
 * Created by mandark on 28-02-2016.
 */
public class FunctionCoefficients {
    private final String productId;
    private final double intercept;
    private final double slope;
    private final CoefficientsType type;

    public FunctionCoefficients(String prodctId, double intercept, double slope, CoefficientsType type) {
        this.productId = prodctId;
        this.intercept = intercept;
        this.slope = slope;
        this.type = type;
    }

    public String getProductId() {
        return this.productId;
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
