package com.test.annova;

import org.apache.commons.math3.stat.inference.OneWayAnova;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandar on 04-09-2016.
 */
public class AnnovaTest {

    private static final double SIGNIFICANCE_LEVEL = 0.001; // 99.9%

    public static void main(String[] args) {

        double[][] observations = {
                {150.0, 200.0, 180.0, 230.0, 220.0, 250.0, 230.0, 300.0, 190.0},
                {200.0, 240.0, 220.0, 250.0, 210.0, 190.0, 240.0, 250.0, 190.0}
        };

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
