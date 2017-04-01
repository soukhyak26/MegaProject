package com.interpolation.cubicspline.var1;

import com.interpolation.linear.SimpleLinearInterpolator;

/**
 * Created by mandark on 24-10-2015.
 */
public class SplineInterpolationTest {
    public static void main(String [] args){
    double [] x= new double[] {31,59,90,120,151,181,212,243,273,304,334,365};
    double [] y= new double[]{22500,43500,64500,91000,117475,143160,172860,202135,231420,260345,293245,325395};
        SimpleLinearInterpolator interpol = new SimpleLinearInterpolator();
         double[] results = interpol.interpolate(x,y);
        for( int i=0;i<results.length;i++) {
            System.out.println(results[i]);
        }
    }
}
