package com.test.annova;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.regression.SimpleRegression;

public class RegressionComparison {

    public static void main(String[] args) {
        // Example data for two sets of observations
        double[] xData1 = {1, 2, 3, 4, 5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
        double[] yData1 = {1, 2, 3, 4, 5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};

        double[] xData2 = {1, 2, 3, 4, 5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
        //double[] yData2 = {1, 3, 4, 3, 4};
        double[] yData2 = {2, 4, 6, 8, 10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,42,44,46,48,50,52,54,56,58,60};

        // Perform simple linear regression for the first set of observations
        SimpleRegression regression1 = new SimpleRegression();
        for (int i = 0; i < xData1.length; i++) {
            regression1.addData(xData1[i], yData1[i]);
        }

        // Perform simple linear regression for the second set of observations
        SimpleRegression regression2 = new SimpleRegression();
        for (int i = 0; i < xData2.length; i++) {
            regression2.addData(xData2[i], yData2[i]);
        }

        // Compare regression lines with hypothesis tests
        double pValue = performHypothesisTest(regression1, regression2);

        // Output the results
        System.out.println("Regression Line 1: y = " + regression1.getSlope() + "x + " + regression1.getIntercept());
        System.out.println("Regression Line 2: y = " + regression2.getSlope() + "x + " + regression2.getIntercept());

        System.out.println("Hypothesis Test p-value: " + pValue);

        // Interpret the results based on the p-value
        if (pValue < 0.05) {
            System.out.println("The regression lines are significantly different.");
        } else {
            System.out.println("There is no significant difference between the regression lines.");
        }
    }

    /*private static double performHypothesisTest(SimpleRegression regression1, SimpleRegression regression2) {
        OLSMultipleLinearRegression olsRegression = new OLSMultipleLinearRegression();
        double[][] xMatrix = {{1, 0}, {0, 1}};
        double[] yData = {regression1.getR(), regression2.getR()};
        olsRegression.newSampleData(yData, xMatrix);

        return olsRegression.estimateRegressionParameters()[1];
    }*/

    /*private static double performHypothesisTest(SimpleRegression regression1, SimpleRegression regression2) {
        OLSMultipleLinearRegression olsRegression = new OLSMultipleLinearRegression();
        double[][] xMatrix = new double[2][2];
        double[] yData = new double[2];

        // Set up the matrix for the hypothesis test
        xMatrix[0][0] = 1;
        xMatrix[0][1] = regression1.getR();
        yData[0] = regression1.getR();

        xMatrix[1][0] = 1;
        xMatrix[1][1] = regression2.getR();
        yData[1] = regression2.getR();

        olsRegression.newSampleData(yData, xMatrix);

        // The p-value is the last element of the estimateRegressionParameters array
        return olsRegression.estimateRegressionParameters()[1];
    }
*/
    private static double performHypothesisTest(SimpleRegression regression1, SimpleRegression regression2) {
        // Get the number of observations for each regression
        int n1 = (int) regression1.getN();
        int n2 = (int) regression2.getN();

        // Get the slopes and standard errors
        double slope1 = regression1.getSlope();
        double slope2 = regression2.getSlope();
        double stdErr1 = regression1.getSlopeStdErr();
        double stdErr2 = regression2.getSlopeStdErr();

        // Calculate the standard error of the difference between slopes
        double stdErrDiff = Math.sqrt((stdErr1 * stdErr1) / n1 + (stdErr2 * stdErr2) / n2);

        // Calculate the t-statistic for the difference between slopes
        double tStatistic = (slope1 - slope2) / stdErrDiff;

        // Calculate the degrees of freedom for the t-distribution
        int df = n1 + n2 - 4; // 2 predictors for each regression model

        // Create a t-distribution with the calculated degrees of freedom
        TDistribution tDistribution = new TDistribution(df);

        // Calculate the p-value for the two-sided test
        double pValue = 2 * (1 - tDistribution.cumulativeProbability(Math.abs(tStatistic)));

        return pValue;
    }

}
