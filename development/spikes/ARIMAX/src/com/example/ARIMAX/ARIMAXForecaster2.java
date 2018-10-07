package com.example.ARIMAX;

import breeze.storage.Zero;
import com.cloudera.sparkts.models.ARIMAX;
import com.cloudera.sparkts.models.ARIMAXModel;
import org.apache.commons.lang.ArrayUtils;
import org.apache.spark.mllib.linalg.DenseMatrix;
import org.apache.spark.mllib.linalg.DenseVector;
import scala.Option;
import scala.reflect.ClassTag;

import java.io.*;
import java.util.Arrays;

public class ARIMAXForecaster2 {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        double[] M_demand = getTrainData(0, 1);
        double[] S1_demand = getTrainData(1, 2);
        double[] S2_demand = getTrainData(2, 3);

        double[] M_demandTest = getTestData(0, 1);
        double[] S1_demandTest = getTestData(1, 2);
        double[] S2_demandTest = getTestData(2, 3);


        ClassTag<Object> tag = scala.reflect.ClassTag$.MODULE$.apply(double.class);
        Zero zero = Zero.DoubleZero$.MODULE$;
        DenseVector tsTrain = new DenseVector(M_demand);
        DenseMatrix xregTrainSpark = new DenseMatrix(S1_demand.length,2,ArrayUtils.addAll(S1_demand,S2_demand));
        breeze.linalg.DenseMatrix xregTrain = xregTrainSpark.asBreeze().toDenseMatrix(tag,zero);
        DenseVector tsTestSpark = new DenseVector(M_demandTest);
        breeze.linalg.DenseVector tsTest= tsTestSpark.asBreeze().toDenseVector(tag);
        DenseMatrix xregTestSpark = new DenseMatrix(S1_demandTest.length,2, ArrayUtils.addAll(S1_demandTest,S2_demandTest));
        breeze.linalg.DenseMatrix xregTest = xregTestSpark.asBreeze().toDenseMatrix(tag,zero);
        ARIMAXModel arimaxModel = ARIMAX.fitModel(1, 0, 1, tsTrain, xregTrain, 1, true,true, Option.apply(null));

        breeze.linalg.DenseVector forecast = arimaxModel.forecast(tsTest, xregTest);
        for(int i=0; i< forecast.length();i++) {
            System.out.println("forecast of next 20 observations: " + forecast.apply(i));
        }
    }


    public static double[] getTrainData(int col1, int col2) {
        BufferedReader fileReader = null;
        try {
            fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("E:\\apps\\affaince\\development\\spikes\\BoxJenkinsSpike\\data\\demands3.csv"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] train = fileReader.lines().map(String::trim).toArray(String[]::new);
        Double[] filteredArray = Arrays.stream(train).skip(1).flatMap(line -> Arrays.stream(Arrays.copyOfRange(line.split(",", 3), col1, col2))).map(s->Double.parseDouble(s)).toArray(Double[]::new);
        return ArrayUtils.toPrimitive(filteredArray);
    }

    public static double[] getTestData(int col1, int col2) {
        BufferedReader fileReader = null;
        try {
            fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("E:\\apps\\affaince\\development\\spikes\\BoxJenkinsSpike\\data\\demands4.csv"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] train = fileReader.lines().map(String::trim).toArray(String[]::new);
        Double[] filteredArray = Arrays.stream(train).skip(1).flatMap(line -> Arrays.stream(Arrays.copyOfRange(line.split(",", 3), col1, col2))).map(s->Double.parseDouble(s)).toArray(Double[]::new);
        return ArrayUtils.toPrimitive(filteredArray);
    }
}

