package com.affaince.subscription.pricing.command.domain.services;

import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

/**
 * Created by mandark on 28-11-2015.
 */
public class MathsProcessingService {

    public double [] cubicSplineInterpolate(double [] x, double [] y) {
        double[] interpolationResult = new double[365];
        SplineInterpolator interpol = new SplineInterpolator();
        PolynomialSplineFunction f = interpol.interpolate(x, y);
        for (int i = 0; i < 365; i++) {
            int index = -1;
            for(int j=0;j<x.length;j++){
                if( x[j]==i){
                    index= j;
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
}
