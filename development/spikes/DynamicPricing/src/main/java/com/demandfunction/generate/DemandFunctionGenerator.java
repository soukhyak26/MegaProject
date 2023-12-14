package com.demandfunction.generate;

import java.util.function.Function;

public class DemandFunctionGenerator {
        // Generate a linear demand function: quantity = a - b * price
        public static Function<Double, Double> generateLinearDemandFunction(double a, double b) {
            return price -> a - b * price;
        }

        public static void main(String[] args) {
            double a = 100.0; // Intercept of the demand function
            double b = 2.0;   // Slope of the demand function

            Function<Double, Double> linearDemandFunction = generateLinearDemandFunction(a, b);

            // Test the demand function with a price
            double price = 20.0;
            double quantityDemanded = linearDemandFunction.apply(price);

            System.out.println("At a price of $" + price + ", the quantity demanded is " + quantityDemanded);
        }
    }

