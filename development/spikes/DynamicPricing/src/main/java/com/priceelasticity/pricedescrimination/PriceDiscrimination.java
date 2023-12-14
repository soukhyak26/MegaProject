package com.priceelasticity.pricedescrimination;

import java.util.function.Function;

public class PriceDiscrimination {
/*
    public static double calculatePriceElasticity(Function<Double, Double> demandCurve, double price) {
        double quantityDemanded = demandCurve.apply(price);
        double elasticity = (quantityDemanded / price) * (1 / demandCurve.apply(price));
        return elasticity;
    }
*/

    public static double firstDegreePriceDiscrimination(Function<Double, Double> demandCurve) {
        double maxWillingnessToPay = Double.MIN_VALUE;
        double maxPrice = 0.0;

        for (double price = 1.0; price <= 100.0; price++) {
            double quantityDemanded = demandCurve.apply(price);
            double willingnessToPay = quantityDemanded * price;

            if (willingnessToPay > maxWillingnessToPay) {
                maxWillingnessToPay = willingnessToPay;
                maxPrice = price;
            }
        }

        return maxPrice;
    }

    public static void main(String[] args) {
        // Define a simple linear demand curve (quantity demanded = 100 - 2 * price)
        Function<Double, Double> linearDemandCurve = price -> 100 - 2 * price;

        // Simulate market with the linear demand curve
        double maxPrice = firstDegreePriceDiscrimination(linearDemandCurve);
        System.out.println("Max Price for First Degree Price Discrimination: " + maxPrice);
    }
}
