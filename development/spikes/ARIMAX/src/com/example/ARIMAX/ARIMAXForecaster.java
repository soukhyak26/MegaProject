/*
package com.example.ARIMAX;


import breeze.linalg.Options;
import breeze.storage.Zero;
import com.cloudera.sparkts.models.ARIMA;
import com.cloudera.sparkts.models.ARIMAX;
import com.cloudera.sparkts.models.ARIMAXModel;
import org.apache.commons.lang.ArrayUtils;
import org.apache.spark.ml.linalg.Vectors;
import org.apache.spark.mllib.linalg.DenseMatrix;
import org.apache.spark.mllib.linalg.DenseVector;
import scala.None$;
import scala.Option;
import scala.reflect.ClassTag;

import java.io.*;
import java.util.Arrays;

import static scala.None$.MODULE$;


import java.io.FileNotFoundException;
import java.io.IOException;

public class ARIMAXForecaster {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        double[] gdpTrain = getTrainData(3, 4);
        double[] salesTrain = getTrainData(1, 2);
        double[] adBudgetTrain = getTrainData(2, 3);

        double[] gdpTest = getTestData(3, 4);
        double[] salesTest = getTestData(1, 2);
        double[] adBudgetTest = getTestData(2, 3);


        ClassTag<Object> tag = scala.reflect.ClassTag$.MODULE$.apply(double.class);
        Zero zero = (Zero) Zero.DoubleZero$.MODULE$;
        DenseVector tsTrain = new DenseVector(gdpTrain);
        DenseMatrix xregTrainSpark = new DenseMatrix(salesTrain.length,2,ArrayUtils.addAll(salesTrain,adBudgetTrain));
        breeze.linalg.DenseMatrix xregTrain = xregTrainSpark.asBreeze().toDenseMatrix(tag,zero);
        DenseVector tsTestSpark = new DenseVector(gdpTest);
        breeze.linalg.DenseVector tsTest= tsTestSpark.asBreeze().toDenseVector(tag);
        DenseMatrix xregTestSpark = new DenseMatrix(salesTest.length,2, ArrayUtils.addAll(salesTest,adBudgetTest));
        breeze.linalg.DenseMatrix xregTest = xregTestSpark.asBreeze().toDenseMatrix(tag,zero);
        ARIMAXModel arimaxModel = ARIMAX.fitModel(0, 0, 1, tsTrain, xregTrain, 1, true,true, scala.Option.apply(null));

        breeze.linalg.DenseVector forecast = arimaxModel.forecast(tsTest, xregTest);
        for(int i=0; i< forecast.length();i++) {
            System.out.println("forecast of next 20 observations: " + forecast.apply(i));
        }
    }


    public static double[] getTrainData(int col1, int col2) {
        BufferedReader fileReader = null;
        try {
            fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("E:\\apps\\affaince\\development\\spikes\\BoxJenkinsSpike\\data\\data_train.csv"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] train = fileReader.lines().map(String::trim).toArray(String[]::new);
        Double[] filteredArray = Arrays.stream(train).skip(1).flatMap(line -> Arrays.stream(Arrays.copyOfRange(line.split(",", 4), col1, col2))).map(s->Double.parseDouble(s)).toArray(Double[]::new);
        return ArrayUtils.toPrimitive(filteredArray);
    }

    public static double[] getTestData(int col1, int col2) {
        BufferedReader fileReader = null;
        try {
            fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("E:\\apps\\affaince\\development\\spikes\\BoxJenkinsSpike\\data\\data_test.csv"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] train = fileReader.lines().map(String::trim).toArray(String[]::new);
        Double[] filteredArray = Arrays.stream(train).skip(1).flatMap(line -> Arrays.stream(Arrays.copyOfRange(line.split(",", 4), col1, col2))).map(s->Double.parseDouble(s)).toArray(Double[]::new);
        return ArrayUtils.toPrimitive(filteredArray);
    }

}

*/
