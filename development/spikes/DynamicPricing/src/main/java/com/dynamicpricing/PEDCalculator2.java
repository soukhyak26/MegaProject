package com.dynamicpricing;

import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;
public class PEDCalculator2 {
    public static void main(String[] args) {
        // Sample historical data: Price and Quantity Demanded
        double[] prices = {10, 20, 30, 40, 50};
        double[] quantities = {100, 80, 60, 40, 20};

        // Create a WeightedObservedPoints object to hold the data
        WeightedObservedPoints obs = new WeightedObservedPoints();
        for (int i = 0; i < prices.length; i++) {
            obs.add(prices[i], quantities[i]);
        }

        // Fit a linear model to the data
        double[] coefficients = PolynomialCurveFitter.create(1).fit(obs.toList());

        // Extract the slope (coefficient) from the fitted model
        double slope = coefficients[1];

        // Calculate the absolute value of PED
        double absolutePED = Math.abs(slope * (prices[0] / quantities[0]));

        System.out.println("Price Elasticity of Demand (PED): " + absolutePED);
    }
}
