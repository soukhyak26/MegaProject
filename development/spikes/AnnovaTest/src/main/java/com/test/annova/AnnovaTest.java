package com.test.annova;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.stat.inference.OneWayAnova;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mandar on 04-09-2016.
 */
public class AnnovaTest {

    private static final double SIGNIFICANCE_LEVEL = 0.001; // 99.9%

    public static void main(String[] args) throws FileNotFoundException, IOException {

        BufferedReader dailyDatafileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("E:\\apps\\affaince\\development\\spikes\\AnnovaTest\\dailydata.csv"))));
        BufferedReader monthlyDatafileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("E:\\apps\\affaince\\development\\spikes\\AnnovaTest\\monthlydata.csv"))));
        Map<Double, Double> xyDaily = new HashMap<>();
        Map<Double, Double> xyMonthly = new HashMap<>();

        dailyDatafileReader.lines().map(line -> line.split(",", 2)).map(array -> xyDaily.put(Double.parseDouble(array[0]), Double.parseDouble(array[1])));
        monthlyDatafileReader.lines().map(line -> line.split(",", 2)).map(array -> xyMonthly.put(Double.parseDouble(array[0]), Double.parseDouble(array[1])));

        Double[] xDaily = xyDaily.keySet().toArray(new Double[0]);
        Double[] yDaily = xyDaily.values().toArray(new Double[0]);

        Double[] xMonthly = xyMonthly.keySet().toArray(new Double[0]);
        Double[] yMonthly = xyMonthly.values().toArray(new Double[0]);


        Interpolator interpolator = new Interpolator();
        double[] interpolatedTotalSubscriptionsPerDay = interpolator.cubicSplineInterpolate(ArrayUtils.toPrimitive(xMonthly), ArrayUtils.toPrimitive(yMonthly));

        double[][] observations = {interpolatedTotalSubscriptionsPerDay, ArrayUtils.toPrimitive(yDaily)};
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
}
