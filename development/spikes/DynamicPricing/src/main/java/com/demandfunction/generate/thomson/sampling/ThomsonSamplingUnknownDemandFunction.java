package com.demandfunction.generate.thomson.sampling;
import org.apache.commons.math3.distribution.BetaDistribution;
import org.apache.commons.math3.random.RandomDataGenerator;
public class ThomsonSamplingUnknownDemandFunction {
    public static void main(String[] args) {
        // Number of price points (arms)
        int numArms = 5;

        // Number of rounds (pricing opportunities)
        int numRounds = 1000;

        // Initialize variables for Thomson Sampling
        int[] successes = new int[numArms];
        int[] failures = new int[numArms];

        // Run Thomson Sampling
        for (int round = 1; round <= numRounds; round++) {
            // Sample from the Beta distribution for each arm
            double[] sampledRates = new double[numArms];
            for (int arm = 0; arm < numArms; arm++) {
                sampledRates[arm] = sampleFromBetaDistribution(successes[arm] + 1, failures[arm] + 1);
            }

            // Choose the arm with the highest sampled rate
            int selectedArm = chooseArmWithMaxSample(sampledRates);

            // Simulate conversion for the selected arm
            boolean conversion = simulateConversion(selectedArm);

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

    // Function to simulate conversion for the selected arm
    private static boolean simulateConversion(int selectedArm) {
        // Placeholder; in a real-world scenario, you would simulate the conversion based on the pricing strategy
        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        double conversionProbability = 0.2 + 0.1 * selectedArm;  // Simulated conversion probability
        return randomDataGenerator.nextBinomial(1, conversionProbability) == 1;
    }

    // Function to sample from the Beta distribution
    private static double sampleFromBetaDistribution(int alpha, int beta) {
        BetaDistribution betaDistribution = new BetaDistribution(alpha, beta);
        return betaDistribution.sample();
    }

}
