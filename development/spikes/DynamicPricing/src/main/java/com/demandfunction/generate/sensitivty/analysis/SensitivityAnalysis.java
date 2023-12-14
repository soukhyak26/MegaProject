package com.demandfunction.generate.sensitivty.analysis;

public class SensitivityAnalysis {
    public static void main(String[] args) {
        // Sample data
        double initialQuantity = 1000;
        double initialPrice = 10.0;
        double estimatedPED = -1.5; // Example estimated PED

        // Define a range of price changes for sensitivity analysis
        double[] priceChanges = {-0.2, -0.1, 0.0, 0.1, 0.2};

        System.out.println("Price Change | New Quantity | New Revenue | New Profit");

        for (double priceChange : priceChanges) {
            double newPrice = initialPrice * (1 + priceChange);
            double newQuantity = initialQuantity * (1 + estimatedPED * priceChange);

            double newRevenue = newPrice * newQuantity;
            double newCost = calculateCost(newQuantity); // Example cost function, replace with actual cost calculation
            double newProfit = newRevenue - newCost;

            System.out.printf("%.2f          | %.2f        | %.2f       | %.2f%n", priceChange, newQuantity, newRevenue, newProfit);
        }
    }

    // Example cost function, replace with actual cost calculation
    public static double calculateCost(double quantity) {
        return 0.5 * quantity; // Assuming a simple linear cost function for demonstration purposes
    }
}
