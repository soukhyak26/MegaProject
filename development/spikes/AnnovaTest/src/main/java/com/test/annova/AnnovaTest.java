package com.test.annova;

import org.apache.commons.math3.stat.inference.OneWayAnova;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by mandar on 04-09-2016.
 */
public class AnnovaTest {

    private static final double SIGNIFICANCE_LEVEL = 0.05; // 99.9%

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Map<Double, Double> xyDaily = new HashMap<>();
        Map<Double, Double> xyMonthly = new HashMap<>();
        double[] xDaily = null;
        double[] yDaily = null;
        double[][] dailyReadings = null;
        double[][] monthlyReadings = null;
        double[] xMonthly = null;
        double[] yMonthly = null;


        try (BufferedReader dailyDatafileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("dailydata1.csv"))))) {
            dailyReadings = dailyDatafileReader.lines().map(line -> line.trim().split(",")).map(sa -> Stream.of(sa).mapToDouble(Double::parseDouble).toArray()).toArray(double[][]::new);
            xDaily = new double[dailyReadings.length];
            yDaily = new double[dailyReadings.length];
            for (int i = 0; i < dailyReadings.length; i++) {
                xDaily[i] = dailyReadings[i][0];
                yDaily[i] = dailyReadings[i][1];
            }
        }
        try (BufferedReader monthlyDatafileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("dailydata3.csv"))))) {
            monthlyReadings = monthlyDatafileReader.lines().map(line -> line.trim().split(",")).map(sa -> Stream.of(sa).mapToDouble(Double::parseDouble).toArray()).toArray(double[][]::new);
            xMonthly = new double[monthlyReadings.length];
            yMonthly = new double[monthlyReadings.length];
            for (int i = 0; i < monthlyReadings.length; i++) {
                xMonthly[i] = monthlyReadings[i][0];
                yMonthly[i] = monthlyReadings[i][1];
            }

        }

/*
        Interpolator interpolator = new Interpolator();
        double[] interpolatedTotalSubscriptionsPerDay = interpolator.cubicSplineInterpolate(xMonthly, yMonthly);
*/

        double[][] observations = {yMonthly, yDaily};
        final List<double[]> classes = new ArrayList<double[]>();
        for (int i = 0; i < observations.length; i++) {
            classes.add(observations[i]);
        }

        OneWayAnova anova = new OneWayAnova();
        double fStatistic = anova.anovaFValue(classes); // F-value
        double pValue = anova.anovaPValue(classes);     // P-value
        System.out.println("f-value:" + fStatistic);
        System.out.println("p-value:" + pValue);
        boolean rejectNullHypothesis = anova.anovaTest(classes, SIGNIFICANCE_LEVEL);
        System.out.println("reject null hipothesis " + (100 - SIGNIFICANCE_LEVEL * 100) + "% = " + rejectNullHypothesis);
    }
    public static double findVariabilityAmongGroups(double[] interpolatedTotalSubscriptionsPerDay,double[] yDaily){
        double interpolatedTotalSubscriptionsPerDayMean= findMean(interpolatedTotalSubscriptionsPerDay);
        double yDailyMean=findMean(yDaily);
        double grandSum=0;
        for(int i=0;i<interpolatedTotalSubscriptionsPerDay.length;i++){
            grandSum += interpolatedTotalSubscriptionsPerDay[i];
        }
        for(int j=0;j<yDaily.length;j++){
            grandSum +=yDaily[j];
        }
        double grandMean=grandSum/(interpolatedTotalSubscriptionsPerDay.length+yDaily.length);
        System.out.println("%%%%grand mean: " + grandMean);
        double variationAmongGroups=Math.pow((grandMean-interpolatedTotalSubscriptionsPerDayMean),2) + Math.pow((grandMean- yDailyMean),2);
        System.out.println("%%%interpolatedTotalSubscriptionPerDayMean: " + interpolatedTotalSubscriptionsPerDayMean);
        System.out.println("%%%dailyMean: " + yDailyMean);
        System.out.println("percentageChange of Actual with Target: " + variationAmongGroups/interpolatedTotalSubscriptionsPerDayMean);
        return variationAmongGroups;
    }
    public static double findMean(double [] array){
        double sum=0;
        for( int i=0;i<array.length;i++){
            sum +=array[i];
        }
        return sum/array.length;
    }
}
