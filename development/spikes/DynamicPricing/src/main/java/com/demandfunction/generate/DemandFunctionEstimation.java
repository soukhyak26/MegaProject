package com.demandfunction.generate;
import org.apache.commons.math3.fitting.WeightedObservedPoints;
import org.apache.commons.math3.stat.regression.SimpleRegression;

public class DemandFunctionEstimation {
    private static double intercept;
    private static double slope;

    public static void defineDemandFunction(double [] prices, double [] quantities) {


        // Create a WeightedObservedPoints object to hold the data
        WeightedObservedPoints obs = new WeightedObservedPoints();
        for (int i = 0; i < prices.length; i++) {
            obs.add(prices[i], quantities[i]);
        }

        // Fit a linear model to the data
        SimpleRegression curve = new SimpleRegression();
        for (int i = 0; i < prices.length; i++) {
            curve.addData(prices[i], quantities[i]);
        }
        intercept = curve.getIntercept();
        slope = curve.getSlope();

/*
        System.out.println("Intercept: " + intercept);
        System.out.println("Slope: " + slope);

        System.out.println("Demand Function: Quantity = " + intercept + " - " + slope + " * Price");

 */
    }


    public static void main(String[] args){
        // Sample data: Price and Quantity Demanded
        double[] prices = {10, 20, 30, 40, 50};
        double[] quantities = {100, 80, 60, 40, 20};
        defineDemandFunction(prices,quantities);
        System.out.println("intercept : " + intercept);
        System.out.println("slope: " + slope);
    }

    public static double getIntercept() {
        return intercept;
    }

    public static double getSlope() {
        return slope;
    }
}
