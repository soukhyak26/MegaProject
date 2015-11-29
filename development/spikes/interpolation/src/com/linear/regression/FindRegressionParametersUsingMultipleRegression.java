package com.linear.regression;

import org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

/**
 * Created by rbsavaliya on 29-11-2015.
 */
public class FindRegressionParametersUsingMultipleRegression {
    public static void main (String args[]) {
        double [][] xData = {
                {2,0}, {1,1}, {8,2}, {5,3}, {6,4}, {4,5}, {7,6}, {9,7}, {8,8}
        };

        double [] data = {
                6, 2, 0, 4, 1, 1, 16, 8, 2, 10, 5, 3, 12, 6, 4, 8, 4, 5, 12, 7, 6, 16, 9, 7, 14, 8, 8
        };

        AbstractMultipleLinearRegression regression = new OLSMultipleLinearRegression();
        regression.newSampleData(data, 9, 2);
        double [] coe = regression.estimateRegressionParameters();
        for (int i=0;i<data.length;i=i+3){
            System.out.print("Y: " + data[0 + i] + ", ");
            System.out.print("X1: " + data[1 + i] + ", ");
            System.out.print("X2: " + data[2 + i] + ", ");
            System.out.print("a: " + coe[0] + ", ");
            System.out.print("b1: " + coe [1] + ", ");
            System.out.print("b1 error: " + regression.estimateRegressionParametersStandardErrors()[0]+ ", ");
            System.out.print("b2: " + coe[2] + ", ");
            System.out.print("b2 error: " + regression.estimateRegressionParametersStandardErrors()[1]+ ", ");
            System.out.print("Error: " + regression.estimateErrorVariance());
            System.out.println();
        }
    }
}
