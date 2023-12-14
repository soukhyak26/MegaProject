package com.dynamicpricing;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

public class PEDBasedDynamicPriceDetermination3 {

    public double[] calculateCoefficients(double[] demand,double[] price, double[] cost){
        double[][] x = new double[demand.length][2];
        double[] y = new double[demand.length];

        for (int i = 0; i < demand.length; i++) {
            x[i][0] = demand[i];
           // x[i][1] = supply[i];
            x[i][1] = cost[i];
           // x[i][3] = marketConditions[i];
            y[i] = price[i];
        }
        // Create a regression model
        OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
        // Add the data to the model
        regression.newSampleData(y, x);
        // Perform the regression
        double[] beta = regression.estimateRegressionParameters();
        return beta;
    }
    public static void main(String[] args) {
            // Sample data: Demand, Supply, Cost, Market Conditions
            double[] demand = {100, 80, 60, 40, 20};
            double[] price = {10, 20, 30, 40, 50};
            //double[] supply = {180, 170, 170, 130, 160};
            double[] cost = {15,15,15,15,15};
            //double[] marketConditions = {0.8, 0.9, 1.2, 1.5, 2.0}; // Example multiplier
            PEDBasedDynamicPriceDetermination3 calculator = new PEDBasedDynamicPriceDetermination3();

            // Prepare the data for regression
            double[] beta = calculator.calculateCoefficients(demand,price,/*supply,*/cost/*,marketConditions*/);
        // Extract the coefficients
            double demandCoefficient = beta[1];
            //double supplyCoefficient = beta[2];
            double costCoefficient = beta[2];
            //double marketCoefficient = beta[4];
            System.out.println("demandCoefficient: " + demandCoefficient);
            //System.out.println("supplyCoefficient: " + supplyCoefficient);
            System.out.println("costCoefficient: " + costCoefficient);
            //System.out.println("marketCoefficient: "+ marketCoefficient);
            // Example: Determine dynamic price for new conditions
            double newDemand = 110;
            double newSupply = 250;
            double newCost = 15;
            double newMarketConditions = 1.2;

            double dynamicPrice = calculatePrice(newDemand, newCost,
                    demandCoefficient, costCoefficient);

            System.out.println("Dynamic Price: $" + dynamicPrice);
        }

        // Example price determination function
        public static double calculatePrice(double demand, double cost,
                                            double demandCoefficient,
                                            double costCoefficient) {
            return demandCoefficient * demand
                    + costCoefficient * cost ;
        }
}


