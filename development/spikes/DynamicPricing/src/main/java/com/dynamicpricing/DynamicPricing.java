package com.dynamicpricing;

import com.demandfunction.generate.vo.DemandFunctionOutput;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.function.Function;

public class DynamicPricing {

    public double adjustPriceBasedOnElasticity(Function<Double, Double> demandCurve, double currentPrice, double targetElasticity) {
        double currentElasticity = calculatePriceElasticity(demandCurve, currentPrice);
        System.out.println("current elasticity : " + currentElasticity);
        double priceAdjustment = 0.0;
        if (currentElasticity != 0) {
            priceAdjustment = (targetElasticity / currentElasticity - 1) * currentPrice;
        }
        return currentPrice + priceAdjustment;
    }
    public double calculatePriceElasticity(Function<Double, Double> demandCurve, double price) {
        double quantityDemanded = demandCurve.apply(price);
        double elasticity = (quantityDemanded / price) * (1 / demandCurve.apply(price));
        return elasticity;
    }

    public DemandFunctionOutput defineDemandFunction(double [] prices, double [] quantities) {
        // Fit a linear model to the data
        SimpleRegression curve = new SimpleRegression();
        for (int i = 0; i < prices.length; i++) {
            curve.addData(prices[i], quantities[i]);
        }
        double intercept = curve.getIntercept();
        double slope = curve.getSlope();

        return new DemandFunctionOutput(intercept,slope);
    }


    public static void main(String[] args) {
        // Define a simple linear demand curve (quantity demanded = 100 - 2 * price)
        //Function<Double, Double> linearDemandCurve = price -> 100 - 2 * price;
        DynamicPricing pricing = new DynamicPricing();
        double[] prices = {10, 14, 16, 18, 20};
        double[] quantities = {100, 80, 60, 40, 20};
        DemandFunctionOutput demandFunctionOutput = pricing.defineDemandFunction(prices,quantities);
        double intercept= demandFunctionOutput.getIntercept();
        double slope = demandFunctionOutput.getSlope();
        System.out.println("intercept: " + intercept + " slope: " + slope);
        Function<Double, Double> linearDemandCurve = price -> intercept - slope * price;
        // Simulate adjusting price based on elasticity
        double currentPrice = 60.0;
        double targetElasticity = -0.03; // Example target elasticity

        double adjustedPrice = pricing. adjustPriceBasedOnElasticity(linearDemandCurve, currentPrice, targetElasticity);
        System.out.println("Adjusted Price: " + adjustedPrice);
    }
}
