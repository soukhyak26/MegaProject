package com.interpolation.cubicspline.var2;

import com.interpolation.cubicspline.var1.CubicSplineInterpolator;
import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

/**
 * Created by mandark on 24-10-2015.
 */
public class SplineInterpolationTest {
    private double [] x= new double[] {1,31,59,90,120,151,181,212,243,273,304,334,365};
    private double [] y= new double[]{0,22500,43500,64500,91000,117475,143160,172860,202135,231420,260345,293245,325395};

    public static void main(String [] args){
            SplineInterpolationTest t= new SplineInterpolationTest();
            t.getResults();
    }

     public void getResults(){
         double [] interpolationResult = new double[365];
         double [] interpolationResult2 = new double[365];
         SplineInterpolator interpol = new SplineInterpolator();
         CubicSplineInterpolator interpol2= new CubicSplineInterpolator(x,y);
         PolynomialSplineFunction f= interpol.interpolate(x,y);
         for(int i=0;i<365;i++){
             int index=findIndexInX(i);
             if(index>0){
                 interpolationResult[i]=y[index];
                 interpolationResult2[i]=y[index];
             }else{
                 interpolationResult[i]= f.value(i+1);
                 interpolationResult2[i]=interpol2.getValue(i+1);
             }
         }

         for(int i=0;i<365;i++){
             System.out.println(interpolationResult[i]);
         }
         System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
         for(int i=0;i<365;i++){
             System.out.println(interpolationResult2[i]);
         }

     }
     private int findIndexInX(int i){
        for(int j=0;j<x.length;j++){
            if( x[j]==i){
                return j;
            }
        }
         return -1;
    }
}
