package com.affaince.subscription.business.services;

import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.RegressionResults;
import org.apache.commons.math3.stat.regression.SimpleRegression;

/**
 * Created by mandark on 28-11-2015.
 */
public class MathsProcessingService {

    public double[] cubicSplineInterpolate(double[] x, double[] y) {
        double[] interpolationResult = new double[365];
        SplineInterpolator interpol = new SplineInterpolator();
        PolynomialSplineFunction f = interpol.interpolate(x, y);
        for (int i = 0; i < 365; i++) {
            int index = -1;
            for (int j = 0; j < x.length; j++) {
                if (x[j] == i) {
                    index = j;
                }
            }

            if (index > 0) {
                interpolationResult[i] = y[index];
            } else {
                interpolationResult[i] = f.value(i + 1);
            }
        }
        return interpolationResult;
    }

    public RegressionResults processSimpleLinearRegression(double[][] dataToBeRegressed) {
        SimpleRegression simpleRegression = new SimpleRegression();
        simpleRegression.addData(dataToBeRegressed);
        RegressionResults results = simpleRegression.regress();
        return results;
    }

    public CustomRegressionResults processMultipleLinearRegression(int numberOfVariables, int numberOfObservations, double[] dataToBeRegressed) {
        AbstractMultipleLinearRegression regression = new OLSMultipleLinearRegression();
        regression.newSampleData(dataToBeRegressed, 9, numberOfVariables);
        double[] regrssionParameters = regression.estimateRegressionParameters();
        double[] regressionParamtersStandardErrors = regression.estimateRegressionParametersStandardErrors();
        double estimatedErrorVariance = regression.estimateErrorVariance();
        CustomRegressionResults regressionResults = new CustomRegressionResults(regrssionParameters, regressionParamtersStandardErrors, estimatedErrorVariance);
        return regressionResults;
    }

    public class CustomRegressionResults {
        private final double[] regressionParamters;
        private final double[] regressionParamtersStandardErrors;
        private final double estimatedErrorVariance;

        public CustomRegressionResults(double[] regressionParams, double[] stdErrors, double estimatedErrorVariance) {
            this.regressionParamters = regressionParams;
            this.estimatedErrorVariance = estimatedErrorVariance;
            this.regressionParamtersStandardErrors = stdErrors;
        }

        public double[] getRegressionParamters() {
            return this.regressionParamters;
        }

        public double[] getRegressionParamtersStandardErrors() {
            return this.regressionParamtersStandardErrors;
        }

        public double getEstimatedErrorVariance() {
            return this.estimatedErrorVariance;
        }
    }
}
