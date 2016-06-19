package com.examples.ARIMA;
import com.cloudera.sparkts.models.ARIMA;
import com.cloudera.sparkts.models.ARIMAModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;

import java.io.*;

/**
 * Created by mandar on 18-06-2016.
 */
public class ARIMAForecaster2 {
    public static void  main(String[] args) throws FileNotFoundException,IOException{
        BufferedReader fileReader= new BufferedReader(new InputStreamReader(new FileInputStream(new File("E:\\apps\\affaince\\development\\spikes\\BoxJenkinsSpike\\data\\demands2.tsv"))));
        double[] values= fileReader.lines().mapToDouble(n->Double.parseDouble(n)).toArray();
        Vector ts = Vectors.dense(values);
        ARIMAModel arimaModel = ARIMA.fitModel(1, 0, 1,ts,true,"css-cgd",null);
        double[] coefficients =arimaModel.coefficients();
        for(Double coeff : coefficients) {
            System.out.println("coefficients: " + coeff);
        }
        Vector forecast = arimaModel.forecast(ts, 20);
        for(int i=0; i<forecast.argmax();i++) {
            System.out.println("forecast of next 20 observations: " + forecast.apply(i));
        }
    }
}
