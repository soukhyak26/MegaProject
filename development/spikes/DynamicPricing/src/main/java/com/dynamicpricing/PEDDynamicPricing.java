package com.dynamicpricing;


public class PEDDynamicPricing {

    public static double calculatePED(double initialQuantity, double finalQuantity, double initialPrice, double finalPrice) {
        double percentageChangeInQuantity = ((finalQuantity - initialQuantity) / initialQuantity) * 100;
        double percentageChangeInPrice = ((finalPrice - initialPrice) / initialPrice) * 100;

        return percentageChangeInQuantity / percentageChangeInPrice;
    }

    public static double adjustPriceBasedOnPED(double currentPrice, double currentQuantity, double targetPED, double elasticityThreshold) {
        double targetQuantity = currentQuantity * (1 + targetPED);
        double priceAdjustmentFactor = Math.abs(targetQuantity / currentQuantity - 1);

        if (targetPED > elasticityThreshold) {
            return currentPrice * (1 - priceAdjustmentFactor); // Increase price for elastic demand
        } else {
            return currentPrice * (1 + priceAdjustmentFactor); // Decrease price for inelastic demand
        }
    }

    public static void main(String[] args) {
        // Assume initial conditions
        double initialQuantity = 1000;
        double finalQuantity = 800;
        double initialPrice = 100.0;
        double finalPrice = 120.0;

        double targetPED = -1.2; // Example target PED (elastic demand)
        double elasticityThreshold = -1; // Threshold for elasticity decision

        double currentPED = calculatePED(initialQuantity, finalQuantity, initialPrice, finalPrice);

        System.out.println("Current Price Elasticity of Demand: " + currentPED);

        double adjustedPrice = adjustPriceBasedOnPED(initialPrice, initialQuantity, targetPED, elasticityThreshold);

        System.out.println("Adjusted Price: " + adjustedPrice);
    }
}
