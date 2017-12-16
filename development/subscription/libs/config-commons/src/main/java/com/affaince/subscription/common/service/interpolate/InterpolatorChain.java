package com.affaince.subscription.common.service.interpolate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mandar on 2/28/2017.
 */
public class InterpolatorChain {
    @Autowired
    private SimpleLinearInterpolator simpleLinearInterpolator;
    @Autowired
    private CubicSplineInterpolator cubicSplineInterpolator;

    private Interpolator initialInterpolator;
    @Value("${interpolator.chain.list}")
    private String interpolatorChainElements;

    public InterpolatorChain(){}

    @PostConstruct
    public void init(){
        List<String> interpolatorPrefixes = Arrays.asList(interpolatorChainElements.split(","));
        for (String prefix : interpolatorPrefixes) {

            if(prefix.equals("sli")) {
                this.addInterpolator(simpleLinearInterpolator);
            }else if (prefix.equals("csi")) {
                this.addInterpolator(cubicSplineInterpolator);
            } else {
                this.addInterpolator(cubicSplineInterpolator);
            }
        }

    }

    private void addInterpolator(Interpolator interpolator) {
        if (null != initialInterpolator) {
            initialInterpolator.addNextInterpolator(interpolator);
        } else {
            initialInterpolator = interpolator;
        }
    }

    public double[] interpolate(double[] x, double[] y){
        return initialInterpolator.interpolate(x,y);
    }

}
