package com.examples.ARIMA;

import com.cloudera.sparkts.models.ARIMA;
import com.cloudera.sparkts.models.ARIMAX;
import com.cloudera.sparkts.models.ARIMAXModel;
import org.apache.commons.lang.ArrayUtils;
import org.apache.spark.mllib.linalg.*;
import breeze.linalg.*;
//import org.apache.spark.mllib.linalg.DenseMatrix;
import org.apache.spark.mllib.linalg.DenseVector;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.Vector;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ARIMAXForecaster {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Double[] gdpTrain = getTrainData(3, 4);
        Double[] salesTrain = getTrainData(1, 2);
        Double[] adBudgetTrain = getTrainData(2, 3);

        Double[] gdpTest = getTestData(3, 4);
        Double[] salesTest = getTestData(1, 2);
        Double[] adBudgetTest = getTestData(2, 3);

        DenseVector tsTrain = new DenseVector(ArrayUtils.toPrimitive(gdpTrain));
        breeze.linalg.DenseMatrix xregTrain= new breeze.linalg.DenseMatrix(salesTrain.length,2,ArrayUtils.addAll(salesTrain,adBudgetTrain));

        breeze.linalg.DenseVector tsTest = new breeze.linalg.DenseVector(gdpTest);
        breeze.linalg.DenseMatrix xregTest = new breeze.linalg.DenseMatrix(salesTest.length,2, ArrayUtils.addAll(salesTest,adBudgetTest));

        ARIMAXModel arimaxModel = ARIMAX.fitModel(2, 1, 1,tsTrain,xregTrain,1,true,true,null);
        breeze.linalg.DenseVector forecast = arimaxModel.forecast(tsTest, xregTest);
        for(int i=0; i< forecast.length();i++) {
            System.out.println("forecast of next 20 observations: " + forecast.apply(i));
        }
    }


    public static Double[] getTrainData(int col1, int col2) {
        BufferedReader fileReader = null;
        try {
            fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("E:\\apps\\affaince\\development\\spikes\\BoxJenkinsSpike\\data\\data_train.csv"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] train = fileReader.lines().map(String::trim).toArray(String[]::new);
        Double[] filteredArray = Arrays.stream(train).skip(1).flatMap(line -> Arrays.stream(Arrays.copyOfRange(line.split(",", 4), col1, col2))).map(s->Double.parseDouble(s)).toArray(Double[]::new);
        //Object[] filteredArray = Arrays.stream(train).skip(1).flatMap(line -> Arrays.stream(Arrays.copyOfRange(line.split(",", 4), col1, col2))).toArray();
        //return ArrayUtils.toPrimitive(filteredArray);
        return filteredArray;
    }

    public static Double[] getTestData(int col1, int col2) {
        BufferedReader fileReader = null;
        try {
            fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("E:\\apps\\affaince\\development\\spikes\\BoxJenkinsSpike\\data\\data_test.csv"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] train = fileReader.lines().map(String::trim).toArray(String[]::new);
        Double[] filteredArray = Arrays.stream(train).skip(1).flatMap(line -> Arrays.stream(Arrays.copyOfRange(line.split(",", 4), col1, col2))).map(s->Double.parseDouble(s)).toArray(Double[]::new);
        //Object[] filteredArray = Arrays.stream(train).skip(1).flatMap(line -> Arrays.stream(Arrays.copyOfRange(line.split(",", 4), col1, col2))).toArray();
        //return ArrayUtils.toPrimitive(filteredArray);
        return filteredArray;
    }

}

