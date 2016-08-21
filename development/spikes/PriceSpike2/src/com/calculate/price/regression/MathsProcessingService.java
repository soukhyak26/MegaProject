package com.calculate.price.regression;

import net.sourceforge.openforecast.DataPoint;
import net.sourceforge.openforecast.DataSet;
import net.sourceforge.openforecast.ForecastingModel;
import net.sourceforge.openforecast.Observation;
import net.sourceforge.openforecast.models.MultipleLinearRegressionModel;
import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.RegressionResults;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.Iterator;

/**
 * Created by mandark on 28-11-2015.
 */
public class MathsProcessingService {

    public static double[] cubicSplineInterpolate(double[] x, double[] y) {
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

    public static RegressionResults processSimpleLinearRegression(double[][] dataToBeRegressed) {
        SimpleRegression simpleRegression = new SimpleRegression();
        simpleRegression.addData(dataToBeRegressed);
        RegressionResults results = simpleRegression.regress();
        return results;
    }

    public static RegressionResult processMultipleLinearRegression(double[] dataToBeRegressed, int numberOfRecords, int numberOfVariables) {
        OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
        regression.newSampleData(dataToBeRegressed, numberOfRecords, numberOfVariables);

        return new RegressionResult(regression.estimateRegressionParameters(),regression.calculateAdjustedRSquared());
    }

/*
    public String getMultipleLinearRegressionEquation(double[] observations, int periodPerYear) {
        DataSet observedData = new DataSet();
        DataPoint dp;
        for (int t = 0; t < observations.length; t++) {
            dp = new Observation(observations[t]);
            dp.setIndependentValue("t", t+1);
            observedData.add(dp);
        }
        observedData.setTimeVariable("t");
        observedData.setPeriodsPerYear(periodPerYear);

        MultipleLinearRegressionModel model
                = new MultipleLinearRegressionModel();
        model.init(observedData);

        Set coeffSet = model.getCoefficients().entrySet();
        Iterator it = coeffSet.iterator();
        String equation="" + model.getIntercept();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            double coeff = ((Double)entry.getValue()).doubleValue();
            if(coeff < -1.0E-8D) {
                equation = equation + coeff + "*" + (String)entry.getKey();
            } else if(coeff > 1.0E-8D) {
                equation = equation + "+" + coeff + "*" + (String)entry.getKey();
            }
        }
        return equation;
    }
*/

    public static double[] processForecastUsingTripleExponentialTimeSeries(double[] observations, int periodPerYear) {
        DataSet observedData = new DataSet();
        DataPoint dp;
        for (int t = 0; t < observations.length; t++) {
            dp = new Observation(observations[t]);
            dp.setIndependentValue("t", t+1);
            observedData.add(dp);
        }
        observedData.setTimeVariable("t");
        observedData.setPeriodsPerYear(periodPerYear);

        ForecastingModel model
                = new MultipleLinearRegressionModel();
        model.init(observedData);

        int n = observations.length;
        DataSet requiredDataPoints = new DataSet();
        DataPoint requiredDatePoint;

        for (int count = 0; count < n; count++) {
            dp = new Observation(0.0);
            dp.setIndependentValue("t", count);
            requiredDataPoints.add(dp);
        }
        DataSet results = model.forecast(requiredDataPoints);

        Iterator<DataPoint> it = results.iterator();
        int i = 0;
        double[] resultsArray = new double[results.size()];

        while (it.hasNext()) {
            DataPoint pnt = it.next();
            resultsArray[i] = pnt.getDependentValue();
            i++;
        }
        return resultsArray;
    }
}
