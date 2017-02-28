package com.affaince.subscription.common.service.interpolate;

/**
 * Created by mandar on 2/28/2017.
 */
public interface Interpolator {
    public double[] interpolate(double[] x, double[] y) ;
    public void addNextInterpolator(Interpolator interpolator);
}
