package com.dynamicpricing;

import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

public class PEDCalculator {


    public static void main(String[] args) {
        // Sample data: Price and Quantity Demanded
        double[] prices = {10, 20, 30, 40, 50};
        double[] quantities = {100, 80, 60, 40, 20};

        // Prepare the data for regression
        double[][] x = new double[prices.length][2];
        double[] y = new double[quantities.length];

        for (int i = 0; i < prices.length; i++) {
            x[i][0] = 1;  // Constant term
            x[i][1] = prices[i];
            y[i] = quantities[i];
        }

        // Create a regression model
        OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();

        // Add the data to the model
        regression.newSampleData(y, x);

        // Perform the regression
        double[] beta = regression.estimateRegressionParameters();

        // Extract the coefficient for price (which represents PED)
        double priceCoefficient = beta[2];

        // Calculate the absolute value of PED
        double absolutePED = Math.abs(priceCoefficient);

        System.out.println("Price Elasticity of Demand (PED): " + absolutePED);
    }
}
