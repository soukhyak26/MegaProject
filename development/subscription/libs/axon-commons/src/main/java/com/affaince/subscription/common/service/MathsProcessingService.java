package com.affaince.subscription.common.service;

import net.sourceforge.openforecast.DataPoint;
import net.sourceforge.openforecast.DataSet;
import net.sourceforge.openforecast.ForecastingModel;
import net.sourceforge.openforecast.Observation;
import net.sourceforge.openforecast.models.TripleExponentialSmoothingModel;
import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.RegressionResults;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.Iterator;

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

    public double[] processMultipleLinearRegression(double[] dataToBeRegressed, int numberOfRecords, int numberOfVariables) {
        AbstractMultipleLinearRegression regression = new OLSMultipleLinearRegression();
        regression.newSampleData(dataToBeRegressed, numberOfRecords, numberOfVariables);
        return regression.estimateRegressionParameters();
    }

    public double[] processForecastUsingTripleExponentialTimeSeries(double[] observations, int periodPerYear) {
        DataSet observedData = new DataSet();
        DataPoint dp;
        for (int t = 0; t < observations.length; t++) {
            dp = new Observation(observations[t]);
            dp.setIndependentValue("t", t);
            observedData.add(dp);
        }
        observedData.setPeriodsPerYear(periodPerYear);

        ForecastingModel model
                = new TripleExponentialSmoothingModel(0.5, 0.4, 0.6);
        model.init(observedData);

        int n = observations.length;
        DataSet requiredDataPoints = new DataSet();
        DataPoint requiredDatePoint;

        for (int count = 0; count < n; count++) {
            dp = new Observation(0.0);
            dp.setIndependentValue("x", count);
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
