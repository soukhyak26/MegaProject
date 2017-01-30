package com.affaince.subscription.common.service.interpolate;

import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 14-08-2016.
 */
@Component
public class Interpolator {

    public double[] cubicSplineInterpolate(double[] x, double[] y) {
        int resultLength = Double.valueOf(x[x.length - 1]).intValue();
        double[] interpolationResult = new double[resultLength];
        SplineInterpolator interpol = new SplineInterpolator();
        PolynomialSplineFunction f = interpol.interpolate(x, y);
        for (int i = 0; i < resultLength; i++) {
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

}