package com.demandfunction.generate.linear.deamdn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LinearDemand {

    // Function to calculate the linear demand
    private static double linear(double a, double b, double x) {
        return b + a * x;
    }

    // Function to generate demand hypotheses
    private static List<DemandHypothesis> generateDemandHypotheses(double[] aVec, double[] bVec) {
        List<DemandHypothesis> hypotheses = new ArrayList<>();

        for (double a : aVec) {
            for (double b : bVec) {
                double pOpt = -b / (2 * a);
                DemandFunction demandFunction = x -> linear(a, b, x);
                DemandHypothesis hypothesis = new DemandHypothesis(demandFunction, pOpt);
                hypotheses.add(hypothesis);
            }
        }

        return hypotheses;
    }

    // Function to simulate actual demand (placeholder)
    private static double sampleActualDemand(double price) {
        // Placeholder; you need to implement the actual demand sampling based on the pricing strategy
        double[] demands = new double[]{80,70,60,50,40,30,20,10};
        double[] prices = new double []{5,10,15,20,30,35,40,45};
        int indexFound = Arrays.binarySearch(demands, price);
        return (indexFound < 0) ? -1 : prices[indexFound];
    }

    // Function to generate a price schedule vector
    private static int[] intervals(int m, int T, int scale) {
        List<Integer> mask = new ArrayList<>();
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < scale * Math.ceil(logx(T, m - i)); j++) {
                mask.add(i - 1);
            }
        }
        for (int i = mask.size(); i < T; i++) {
            mask.add(m - 1);
        }

        return mask.stream().mapToInt(Integer::intValue).toArray();
    }

    // Iterative logarithm function
    private static double logx(double x, int n) {
        for (int i = 0; i < n; i++) {
            x = Math.log(x);
            if (x <= 0) {
                x = 0;
            }
        }
        return x;
    }

    public static void main(String[] args) {
        int T = 24 * 1; // Time step is one hour, flash offering for 1 day
        int m = 4;      // Not more than 4 price updates

        int tau = 0;    // Start time of the current interval
        double p = 5.0; // Initial price (Note: The initial price should be set based on your requirements)

        // Initialize a_vec and b_vec with appropriate values
        double[] aVec = {1.0, 2.0};
        double[] bVec = {3.0, 4.0};

        // Generate demand hypotheses
        List<DemandHypothesis> hVec = generateDemandHypotheses(aVec, bVec);

        int[] tMask = intervals(m, T, 2);

        List<Double> histD = new ArrayList<>();
        for (int t = 0; t < T - 1; t++) { // Simulation loop
            double realizedD = sampleActualDemand(p);
            histD.add(realizedD);

            if (tMask[t] != tMask[t + 1]) { // End of the interval
                double intervalMeanD = histD.subList(tau, t + 1).stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

                double minDist = Double.POSITIVE_INFINITY;
                DemandHypothesis hOpt = null;

                for (DemandHypothesis h : hVec) { // Search for the best hypothesis
                    double dist = Math.abs(intervalMeanD - h.getDemandFunction().calculate(p));
                    if (dist < minDist) {
                        minDist = dist;
                        hOpt = h;
                    }
                }

                p = hOpt.getOptimalPrice(); // Set price for the next interval
                tau = t + 1;                // Switch to the next interval
            }
        }
    }
}

// Functional interface for demand function
interface DemandFunction {
    double calculate(double x);
}

// Class representing a demand hypothesis
class DemandHypothesis {
    private final DemandFunction demandFunction;
    private final double optimalPrice;

    public DemandHypothesis(DemandFunction demandFunction, double optimalPrice) {
        this.demandFunction = demandFunction;
        this.optimalPrice = optimalPrice;
    }

    public DemandFunction getDemandFunction() {
        return demandFunction;
    }

    public double getOptimalPrice() {
        return optimalPrice;
    }
}
