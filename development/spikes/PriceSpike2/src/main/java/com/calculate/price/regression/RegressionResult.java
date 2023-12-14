package com.calculate.price.regression;

/**
 * Created by mandark on 21-02-2016.
 */
public class RegressionResult {
    private double[] regressionParameters;
    private double adjustedRSquaredValue;

    public RegressionResult(double[] regressionParameters, double adjustedRSquaredValue) {
        this.regressionParameters = regressionParameters;
        this.adjustedRSquaredValue = adjustedRSquaredValue;
    }

    public double[] getRegressionParameters() {
        return this.regressionParameters;
    }

    public double getAdjustedRSquaredValue() {
        return this.adjustedRSquaredValue;
    }
}
