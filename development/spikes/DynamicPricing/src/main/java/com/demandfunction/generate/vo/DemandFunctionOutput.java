package com.demandfunction.generate.vo;

public class DemandFunctionOutput {
    private double intercept;
    private double slope;

    public DemandFunctionOutput(double intercept, double slope) {
        this.intercept = intercept;
        this.slope = slope;
    }

    public double getIntercept() {
        return intercept;
    }

    public double getSlope() {
        return slope;
    }
}
