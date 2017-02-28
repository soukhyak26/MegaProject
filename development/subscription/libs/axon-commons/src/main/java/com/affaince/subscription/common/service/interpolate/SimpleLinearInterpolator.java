package com.affaince.subscription.common.service.interpolate;

import com.affaince.subscription.common.service.interpolate.config.MaxSizeConstraints;
import com.affaince.subscription.common.service.interpolate.config.MinSizeConstraints;
import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mandar on 2/28/2017.
 */
public class SimpleLinearInterpolator implements Interpolator{
    private Interpolator nextInterpolator;

    @Autowired
    private MinSizeConstraints minSizeConstraints;
    @Autowired
    private MaxSizeConstraints maxSizeConstraints;

    public double[] interpolate(double[] x, double[] y){
        if (y.length > minSizeConstraints.getSli() && y.length <= maxSizeConstraints.getSli()) {
            final int resultLength = Double.valueOf(x[x.length - 1]).intValue();
            double[] interpolationResult = new double[resultLength];
            final LinearInterpolator linearInterpolator = new LinearInterpolator();
            PolynomialSplineFunction psf = linearInterpolator.interpolate(x, y);
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
                    interpolationResult[i] = psf.value(i + 1);
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
