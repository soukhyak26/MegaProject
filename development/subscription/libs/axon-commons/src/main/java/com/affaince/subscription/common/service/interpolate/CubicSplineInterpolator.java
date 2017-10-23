package com.affaince.subscription.common.service.interpolate;

import com.affaince.subscription.common.service.interpolate.config.MaxSizeConstraints;
import com.affaince.subscription.common.service.interpolate.config.MinSizeConstraints;
import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 14-08-2016.
 */
@Component
public class CubicSplineInterpolator implements Interpolator{
    private Interpolator nextInterpolator;

    @Autowired
    private MinSizeConstraints minSizeConstraints;
    @Autowired
    private MaxSizeConstraints maxSizeConstraints;

    public double[] interpolate(double[] x, double[] y) {
        if (y.length > minSizeConstraints.getCsi()) {
            int resultLength = Double.valueOf(x[x.length - 1]).intValue();
            double[] interpolationResult = new double[resultLength];
            //check if x[0] starts with 0 or not..if not then add x[0]=0,y[0]=0.. this is hack
            if (x[0] != 0) {
                double[] newX = new double[x.length + 1];
                double[] newY = new double[y.length + 1];
                newX[0] = 0.0;
                newY[0] = 0.0;
                System.arraycopy(x, 0, newX, 1, x.length);
                System.arraycopy(y, 0, newY, 1, y.length);
                x = newX;
                y = newY;
            }

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
        }else{
            return nextInterpolator == null ? null : nextInterpolator.interpolate(x,y);
        }
    }

    public void addNextInterpolator(Interpolator interpolator){
        this.nextInterpolator =interpolator;
    }
}
