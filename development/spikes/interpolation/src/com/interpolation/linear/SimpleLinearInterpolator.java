package com.interpolation.linear;

import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by mandar on 2/28/2017.
 */
public class SimpleLinearInterpolator {
    public double[] interpolate(double[] x, double[] y) {
        final int resultLength = Double.valueOf(x[x.length - 1]).intValue();
        double[] interpolationResult = new double[resultLength];
        //check if x[0] starts with 0 or not..if not then add x[0]=0,y[0]=0.. this is hack
        if (x[0] != 0) {
            double[] newX = new double[x.length + 1];
            double[] newY = new double[y.length + 1];
            newX[0]=0.0;
            newY[0]=0.0;
            System.arraycopy(x,0,newX,1,x.length);
            System.arraycopy(y,0,newY,1,y.length);
            x=newX;
            y=newY;
        }
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
    }


}
