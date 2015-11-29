package com.linear.regression;

import org.apache.commons.math3.stat.regression.RegressionResults;
import org.apache.commons.math3.stat.regression.SimpleRegression;

/**
 * Created by rbsavaliya on 29-11-2015.
 */
public class FindabUsingSimpleRegression {
    public static void main (String args[]) {
        double [][] data = {
                {1,4}, {2,6}, {4,8}, {8,14}, {6,12}, {5,10}, {8,16}, {9,16}, {7,12}
        };

        SimpleRegression simpleRegression = new SimpleRegression();
        simpleRegression.addData(data);
        RegressionResults results = simpleRegression.regress();
        for (int i=0;i<data.length;i++){
            System.out.print("X: " + data[i][0] + ", ");
            System.out.print("Y: " + data[i][1] + ", ");
            System.out.print("a: " + results.getParameterEstimate(0) + ", ");
            System.out.print("a: " + results.getParameterEstimate(0) + ", ");
            System.out.print("b: " + results.getParameterEstimate(1));
            System.out.print("Error a: " + results.getStdErrorOfEstimate(0) + ", ");
            System.out.print("Error b: " + results.getStdErrorOfEstimate(1));
            System.out.println();
        }
//        for (int i=0; i<results.getNumberOfParameters();i++) {
//            System.out.println("Parameter " + i + " estimate: " + results.getParameterEstimate(i));
//            System.out.println("Error: " + results.getStdErrorOfEstimate(i));
//        }
    }
}
