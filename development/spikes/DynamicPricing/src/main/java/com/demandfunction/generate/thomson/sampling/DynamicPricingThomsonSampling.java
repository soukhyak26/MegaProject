package com.demandfunction.generate.thomson.sampling;

import java.util.Random;

public class DynamicPricingThomsonSampling {

    public static void main(String[] args) {
        // Number of arms (different price points)
        int numArms = 5;

        // True conversion rates for each arm (simulated)
        double[] trueConversionRates = {0.05, 0.1, 0.15, 0.2, 0.25};

        // Number of rounds (pricing opportunities)
        int numRounds = 1000;

        // Run Thomson Sampling
        runThomsonSampling(numArms, trueConversionRates, numRounds);
    }

    // Function to run Thomson Sampling for dynamic pricing
    private static void runThomsonSampling(int numArms, double[] trueConversionRates, int numRounds) {
        Random random = new Random();

        // Initialize variables for Thompson Sampling
        int[] successes = new int[numArms];
        int[] failures = new int[numArms];

        for (int round = 1; round <= numRounds; round++) {
            // Sample from the Beta distribution for each arm
            double[] sampledRates = new double[numArms];
            for (int arm = 0; arm < numArms; arm++) {
                sampledRates[arm] = random.nextDouble(); // Placeholder; should be sampled from Beta distribution
            }

            // Choose the arm with the highest sampled rate
            int selectedArm = chooseArmWithMaxSample(sampledRates);

            // Simulate conversion for the selected arm
            boolean conversion = random.nextDouble() < trueConversionRates[selectedArm];

            // Update successes and failures
            if (conversion) {
                successes[selectedArm]++;
            } else {
                failures[selectedArm]++;
            }

            // Display the selected arm and conversion information (for illustration purposes)
            System.out.println("Round " + round + ": Selected Arm " + selectedArm +
                    ", Conversion: " + conversion +
                    ", Cumulative Successes: " + successes[selectedArm] +
                    ", Cumulative Failures: " + failures[selectedArm]);
        }
    }

    // Function to choose the arm with the maximum sampled rate
    private static int chooseArmWithMaxSample(double[] sampledRates) {
        int maxIndex = 0;
        double maxSample = sampledRates[0];

        for (int i = 1; i < sampledRates.length; i++) {
            if (sampledRates[i] > maxSample) {
                maxSample = sampledRates[i];
                maxIndex = i;
            }
        }

        return maxIndex;
    }
}
